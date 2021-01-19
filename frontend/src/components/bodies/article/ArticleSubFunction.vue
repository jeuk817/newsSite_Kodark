<template>
  <div class="articleSubFunctionContainer">
    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        :color="grayColor"
        v-bind="attrs"
        v-on="on"
        >
          <v-icon>person_add</v-icon>
        </v-btn>
      </template>
      <span>Subscription</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text icon
        :color="grayColor"
        v-bind="attrs"
        v-on="on"
        >
          <v-badge
          :color="grayColor"
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
        :color="grayColor"
        v-bind="attrs"
        v-on="on"
        >
          <v-badge
          :color="grayColor"
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
  </div>
</template>

<script>
export default {
  props: ['commentCount', 'emotions'],
  data() {
    return {
      likeCount: '0',
      hateCount: '0',
      grayColor: 'rgb(150, 150, 150)'
    }
  },
  methods: {
    openCommentWindow() {
      this.$emit('openCommentWindow')
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
  width: 250px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
}

</style>
