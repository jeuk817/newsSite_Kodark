<template>
  <div>
    <div class="articleBody">
      <ArticleTitle :title="title" :subTitle="subTitle" :reporter="reporter" :editedAt="editedAt" />
      <div class="articleTitleMargin">
        <ArticleSubFunction
        :commentCount="commentCount"
        :emotions="emotions"
        @openCommentWindow="openCommentWindow"
        />
      </div>
      <div class="articleContentContainer" id="articleContent"></div>
      <div class="reporterInfo">
        <p>
          By Reporter {{ reporter.name }}
        </p>
        <p>
          Email : {{ reporter.email }}
        </p>
        <p>
          Created at {{ createdAt }}
        </p>
        <p>
          Edited at {{ editedAt }}
        </p>
        <p>
          <ArticleSubFunction
          :commentCount="commentCount"
          :emotions="emotions"
          @openCommentWindow="openCommentWindow"
          />
        </p>
      </div>
    </div>
    <v-navigation-drawer
    app
    right
    width="450px"
    v-model="showCommentWindow"
    >
      <div class="commentCotainer">
        <v-app-bar
        absolute
        color="white"
        elevate-on-scroll
        height="60px"
        class="commentTop"
        >
          <v-btn text icon
          absolute
          right
          @click="closeCommentWindow"
          >
            <v-icon>close</v-icon>
          </v-btn>
          Comments {{ commentCount }}
        </v-app-bar>
        <template v-if="!openCommentForm">
          <div>
            <v-text-field
              placeholder="Share your toughts."
              solo
              @click="openCommentForm = true"
            ></v-text-field>
            <div class="commentWelcomeText">
              Kodark Times needs your voice. We welcome your on-topic commentary, criticism and expertise.
            </div>
          </div>
          <Comment />
          <Comment />
          <Comment />
          <Comment />
        </template>
        <template v-if="openCommentForm">
          <v-textarea
          ref="comment"
          solo
          name="input-7-4"
          label="Enter your voice"
          :rules="commentRules"
          v-model="inputComment"
          ></v-textarea>
          <v-btn depressed class="editBtn text-capitalize white--text"
          color="indigo"
          @click="submitComment"
          >
            Submit
          </v-btn>
          <v-btn depressed class="editBtn text-capitalize white--black"
          color="white"
          @click="closeCommentForm"
          >
            Cancel
          </v-btn>
        </template>
      </div>
    </v-navigation-drawer>
    <v-dialog
      v-model="unauthorized"
      max-width="400"
      @click:outside="moveRoute"
    >
      <v-card>
        <v-card-title class="headline">This function requires sign in.</v-card-title>
        <v-card-text>   
          Would you like to go to the sign in page?
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="moveSignInPage"
          >
            Go
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import ArticleTitle from './ArticleTitle'
import ArticleSubFunction from './ArticleSubFunction'
import Comment from '../../units/Comment'

export default {
  components: {
    ArticleTitle,
    ArticleSubFunction,
    Comment
  },
  data() {
    return {
      title: '',
      subTitle: '',
      content: '',
      createdAt: '',
      editedAt: '',
      commentCount: '0',
      reporter: {},
      emotions: [],
      showCommentWindow: false,
      openCommentForm: false,
      commentRules: [
        v => !!v || 'Comment is required',
        v => v.length <= 250 || 'Comment length must be less than 250'
      ],
      inputComment: '',
      unauthorized: false
    }
  },
  methods: {
    async setArticleDetail() {
      const articleId = this.$route.query.articleId
      const { status, article, links } = await this.$store.dispatch('article/article', { articleId })
      
      if(status === 200) {
        this.title = article.title
        this.subTitle = article.subTitle
        this.content = article.content
        this.createdAt = article.createdAt
        this.editedAt = article.editedAt
        this.commentCount = article.commentCount
        this.reporter = article.reporter
        document.getElementById('articleContent').innerHTML = this.content
      }
    },
    async setEmotionData() {
      const articleId = this.$route.query.articleId
      const { status, emotions, links } = await this.$store.dispatch('article/emotion', { articleId })

      this.emotions = emotions
    },
    // 댓글창 열기
    openCommentWindow() {
      this.showCommentWindow = true
    },
    closeCommentWindow() {
      this.showCommentWindow = false
      this.closeCommentForm()
    },
    closeCommentForm() {
      this.openCommentForm = false
      this.inputComment = ''
    },
    async submitComment() {
      if(!this.$refs.comment.validate(true)) return
      const articleId = this.$route.query.articleId
      const content = this.inputComment
      const { status } = await this.$store.dispatch('users/createComment', { articleId, content })
      console.log(status)
      if(status === 204) {
        this.closeCommentForm()
      } else if(status === 401) {
        this.unauthorized = true
      }
    },
    moveSignInPage() {
      this.unauthorized = false
      this.$router.push({ path: '/en/auth/signIn' })
    }
  },
  created() {
    this.setArticleDetail()
    this.setEmotionData()
  }
}
</script>

<style scoped>
.articleBody {
  padding-top: 100px;
}

.articleTitleMargin {
  margin-left: calc((100% - 600px) / 2);
  margin-right: auto;
  padding: 20px 0;
}

.reporterInfo {
  position: relative;
  max-width: 600px;
  width: 600px;
  left: calc((100% - 600px) / 2);
  padding-top: 43px;
  font-size: 16px;
  font-style: italic;
  border-bottom: 1px solid black;
  margin-bottom: 40px;
}

.commentCotainer {
  padding: 60px 40px 40px 40px;
}

.commentTop {
  font-size: 30px;
  font-weight: bold;
  padding: 0 24px;
}

.commentWelcomeText {
  font-size: 14px;
  padding-bottom: 10px;
  border-bottom: 1px solid rgb(215, 215, 215);
}


</style>

<style>

.articleContentContainer p
, .articleContentContainer div
, .articleContentContainer h1
, .articleContentContainer h2
, .articleContentContainer h3
{
  position: relative;
  max-width: 600px;
  width: 600px;
  font-size: 18px;
  margin: 0 auto 16px auto;
  display: grid;
  justify-content: center;
}

.articleContentContainer img {
  max-width: 1200px;
  object-fit: cover;
  margin: 27px auto 43px auto;
}

</style>

