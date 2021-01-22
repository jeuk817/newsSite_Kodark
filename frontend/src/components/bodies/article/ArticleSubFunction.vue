<template>
  <div class="articleSubFunctionContainer">
    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        :color="grayColor"
        v-bind="attrs"
        v-on="on"
        @click="subscribeReporter"
        >
          <v-icon>person_add</v-icon>
        </v-btn>
      </template>
      <span>Subscription</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        :color="userEmotion === 'like' ? 'blue' : grayColor"
        v-bind="attrs"
        v-on="on"
        @click="chooseEmotion('like')"
        >
          <v-badge
          :color="userEmotion === 'like' ? 'blue' : grayColor"
          :value="likeCount ? true : false"
          :content="likeCount"
          >
            <v-icon>mood</v-icon>
          </v-badge>
        </v-btn>
      </template>
      <span>Like</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        :color="userEmotion === 'hate' ? 'red' : grayColor"
        v-bind="attrs"
        v-on="on"
        @click="chooseEmotion('hate')"
        >
          <v-badge
          :color="userEmotion === 'hate' ? 'red' : grayColor"
          :value="hateCount ? true : false"
          :content="hateCount"
          >
            <v-icon>mood_bad</v-icon>
          </v-badge>
        </v-btn>
      </template>
      <span>Hate</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        v-bind="attrs"
        v-on="on"
        @click="openCommentWindow"
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
      <span>Comments</span>
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
        <v-card-title class="headline">Report article</v-card-title>
        <v-card-text>
          <div>Report Inappropriate Article</div>
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
            @click="reportArticle"
          >
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar
      v-model="snack.show"
      top="true"
      :color="snack.color"
      :timeout="5000"
    >
      {{ snack.msg }}
      <template v-slot:action="{ attrs }">
        <v-btn
          class="text-capitalize"
          dark
          text
          v-bind="attrs"
          @click="snack.show = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
export default {
  props: ['commentCount', 'emotions', 'userEmotion', 'reporter'],
  data() {
    return {
      likeCount: '0',
      hateCount: '0',
      grayColor: 'rgb(150, 150, 150)',
      snack: {
        show: false,
        msg: '',
        color: ''
      },
      reportWindow: false,
      reasons: [],
      reportList: ['Inflammatory', 'Off Topic', 'Personal Attack', 'Vulgar', 'Spam']
    }
  },
  methods: {
    openCommentWindow() {
      this.$emit('openCommentWindow')
    },
    chooseEmotion(emotion) {
      this.$emit('chooseEmotion', emotion)
    },
    async subscribeReporter() {
      const id = this.reporter.id
      const { status } = await this.$store.dispatch('users/subscribeReporter', { id })

      if(status === 201) {
        this.snack.msg = `You have subscribed to ${this.reporter.name || this.reporter.email} reporter.`
        this.snack.color = 'success'
        this.snack.show = true
      } else if(status === 409) {
        this.snack.msg = `You have already subscribed to ${this.reporter.name || this.reporter.email} reporter.`
        this.snack.color = 'info'
        this.snack.show = true
      } else if(status === 401) {
        this.$emit('openUnauthorizedWindow')
      }
    },
    async reportArticle() {
      const reason = this.reasons.join(', ')
      const articleId = this.$route.query.articleId
      const { status } = await this.$store.dispatch('users/reportArticle', { articleId, reason })

      if(status === 201) {
        this.reasons = []
        this.reportWindow = false
        this.snack.msg = 'This article has been reported.'
        this.snack.color = 'success'
        this.snack.show = true
      } else if(status === 401) {
        this.$emit('openUnauthorizedWindow')
      }
    }
  },
  watch: {
    emotions() {
      this.emotions.forEach(el => {
        if(el.emotion === 'like') this.likeCount = el.count
        if(el.emotion === 'hate') this.hateCount = el.count
      });
    }
  }
}
</script>

<style>

</style>

<style scoped>
.articleSubFunctionContainer {
  width: 350px;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
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
