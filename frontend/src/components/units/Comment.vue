<template>
  <div class="comment">
    <div class="commentHeader">
      <v-list-item-avatar color="white darken-3" size="30px">
        <v-img
          class="elevation-6"
          :src="defaultProfileImg"
        ></v-img>
      </v-list-item-avatar>
      <div>
        <div class="font-weight-black">
          {{ comment.user.nickName ? comment.user.nickName : comment.user.email }}
        </div>
        <div>
          {{ comment.user.local ? comment.user.local : 'No local' }}, {{ comment.createdAt}}
        </div>
      </div>
    </div>
    <div class="commentContent">
      <div>
        {{ comment.delFlag === 'F' ? comment.content : 'This comment has been deleted.' }}
      </div>
      <div class="commentFooter">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn text icon
            :color="grayColor"
            v-bind="attrs"
            v-on="on"
            @click="openCommentForm = !openCommentForm"
            >
              <!-- <v-badge
              :color="grayColor"
              :value="commentCount ? true : false"
              :content="commentCount"
              > -->
                <v-icon>chat_bubble_outline</v-icon>
              <!-- </v-badge> -->
            </v-btn>
          </template>
          <span>Reply</span>
        </v-tooltip>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn text icon
            :color="userReputation === 'recommend' ? 'blue' : grayColor"
            v-bind="attrs"
            v-on="on"
            @click="chooseReputation('recommend')"
            >
              <v-badge
              :color="userReputation === 'recommend' ? 'blue' : grayColor"
              :value="comment.reputation.recommend ? true : false"
              :content="comment.reputation.recommend"
              >
                <v-icon>thumb_up_alt</v-icon>
              </v-badge>
            </v-btn>
          </template>
          <span>Recommend</span>
        </v-tooltip>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn text icon
            :color="userReputation === 'decommend' ? 'red' : grayColor"
            v-bind="attrs"
            v-on="on"
            @click="chooseReputation('decommend')"
            >
              <v-badge
              :color="userReputation === 'decommend' ? 'red' : grayColor"
              :value="comment.reputation.decommend ? true : false"
              :content="comment.reputation.decommend"
              >
                <v-icon>thumb_down_alt</v-icon>
              </v-badge>
            </v-btn>
          </template>
          <span>Decommend</span>
        </v-tooltip>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn text icon
            :color="grayColor"
            v-bind="attrs"
            v-on="on"
            @click="reportWindow = !reportWindow"
            >
              <v-icon>report</v-icon>
            </v-btn>
          </template>
          <span>Report</span>
        </v-tooltip>

        <v-dialog
        v-model="reportWindow"
        max-width="290"
        @click:outside="moveRoute"
        >
          <v-card>
            <v-card-title class="headline">Report comment</v-card-title>
            <v-card-text>
              <div>Report Inappropriate Comment</div>
              <div class="reportContainer">
                <v-checkbox
                v-for="reason in reportList" :key="reason"
                class="checkbox" dense v-model="reasons"
                :label="reason" :value="reason"
                />
              </div>
            </v-card-text>
            <div>
            </div>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="red darken-1"
                text
                @click="reportComment"
              >
                Submit
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </div>
      <template v-if="openCommentForm">
        <CommentForm
        @submitComment="submitComment"
        @closeCommentForm="closeCommentForm"
        />
      </template>
      <div v-if="comment.repliesCount">
        {{ comment.repliesCount > 1 ? comment.repliesCount + ' Replies' : comment.repliesCount + ' Reply' }} 
      </div>
      <div class="commentReply">
        <div v-for="(reply, i) in replies" :key="i">
          <CommentReply
          :comment="reply"
          @openUnauthorizedWindow="openUnauthorizedWindow"
          @submitComment="submitComment"
          />
        </div>

        <template v-if="replies.length < comment.repliesCount">
          <v-btn depressed class="editBtn text-capitalize white--black"
          color="white"
          width="100%"
          @click="getMoreReplies"
          >
            More replies
          </v-btn>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import CommentForm from './CommentForm'
import CommentReply from './CommentReply'

export default {
  components: {
    CommentForm,
    CommentReply
  },
  props: ['comment'],
  data() {
    return {
      defaultProfileImg: "/img/k.svg",
      grayColor: 'rgb(150, 150, 150)',
      userReputation: undefined,
      openCommentForm: false,
      replies: [],
      reportWindow: false,
      reasons: [],
      reportList: ['Inflammatory', 'Off Topic', 'Personal Attack', 'Vulgar', 'Spam']
    }
  },
  methods: {
    async chooseReputation(reputation) {
      const commentId = this.comment.id
      const { status, chooseReputation } = await this.$store.dispatch('users/chooseReputation'
                                  , { commentId, reputation })
      
      if(status === 200) {
        if(this.userReputation) this.comment.reputation[this.userReputation]--
        
        if(chooseReputation.length) {
          this.userReputation = chooseReputation[0].reputation
          if(reputation) this.comment.reputation[this.userReputation]++
        }
        else this.userReputation = undefined
      }
      else if(status === 401) {
        if(reputation)
          this.openUnauthorizedWindow()
      }
    },
    openUnauthorizedWindow() {
      this.$emit('openUnauthorizedWindow')
    },
    closeCommentForm() {
      this.openCommentForm = false
    },
    async submitComment(content) {
      const articleId = this.$route.query.articleId
      const commentId = this.comment.id
      const { status } = await this.$store.dispatch('users/createCommentReply', { articleId, commentId, content })

      if(status === 204) {
        this.closeCommentForm()
        this.setCommentReplies(-1)
      } else if(status === 401) {
        this.openUnauthorizedWindow()
      }
    },
    async setCommentReplies(commentStartId) {
      const commentId = this.comment.id
      const { status, commentReplies } = await this.$store.dispatch('article/getCommentReplies', { commentId, commentStartId })

      if(status === 200) {
        this.replies = commentReplies
      }
    },
    async getMoreReplies() {
      const commentId = this.comment.id
      const commentStartId = this.replies[this.replies.length - 1].id
      const { status, commentReplies } = await this.$store.dispatch('article/getCommentReplies', { commentId, commentStartId })

      if(status === 200) {
        const addedReplies = this.replies
        addedReplies.push(...commentReplies)
        this.replies = addedReplies
      }
    },
    async reportComment() {
      const reason = this.reasons.join(', ')
      const commentId = this.comment.id
      const { status } = await this.$store.dispatch('users/reportComment', { commentId, reason })

      if(status === 201) {
        this.reasons = []
        this.reportWindow = false
      } else if(status === 401) {
        this.openUnauthorizedWindow()
      }
    }
  },
  created() {
    this.chooseReputation('')
    this.setCommentReplies(-1)
  },
}
</script>

<style scoped>
.comment {
  margin-top: 20px;
  padding-bottom: 20px;
}

.commentHeader{
  display: grid;
  grid-template-columns: 40px 1fr;
  margin-bottom: 10px;
}

.commentContent {
  margin-left: 40px;
  font-size: 14px;
}

.commentFooter {
  margin-top: 15px;
  width: 250px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
}

.commentReply {
  border-left: 1px solid rgb(215, 215, 215);
}
.reportContainer {
  margin: 5px 0;
}
.checkbox {
  height: 30px;
  margin: 0;
  padding: 0;
}

</style>
