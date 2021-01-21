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
              <v-badge
              :color="grayColor"
              :value="commentCount ? true : false"
              :content="commentCount"
              >
                <v-icon>chat_bubble_outline</v-icon>
              </v-badge>
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
            >
              <v-icon>report</v-icon>
            </v-btn>
          </template>
          <span>Report</span>
        </v-tooltip>
      </div>
      <template v-if="openCommentForm">
        <CommentForm
        @submitComment="submitComment"
        @closeCommentForm="closeCommentForm"
        />
      </template>
    </div>
  </div>
</template>

<script>
import CommentForm from './CommentForm'

export default {
  components: {
    CommentForm
  },
  props: ['comment'],
  data() {
    return {
      defaultProfileImg: "/img/k.svg",
      grayColor: 'rgb(150, 150, 150)',
      userReputation: undefined,
      openCommentForm: false
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
          this.$emit('openUnauthorizedWindow')
      }
    },
    closeCommentForm() {
      this.openCommentForm = false
    },
    async submitComment(content) {
      const articleId = this.$route.query.articleId
      const commentId = this.comment.id
      const { status } = await this.$store.dispatch('users/createCommentReply', { articleId, commentId, content })

      console.log(status)
      if(status === 204) {
        this.closeCommentForm()
      } else if(status === 401) {
        this.$emit('openUnauthorizedWindow')
      }
    }
  },
  created() {
    this.chooseReputation('')
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
</style>