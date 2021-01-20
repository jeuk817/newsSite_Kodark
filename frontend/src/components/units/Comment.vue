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
          <span>Replies</span>
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
            :color="grayColor"
            v-bind="attrs"
            v-on="on"
            >
              <v-badge
              :color="grayColor"
              :value="comment.reputation.decommend ? true : false"
              :content="comment.reputation.decommend "
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
    </div>
  </div>
</template>

<script>
export default {
  props: ['comment'],
  data() {
    return {
      defaultProfileImg: "/img/k.svg",
      grayColor: 'rgb(150, 150, 150)',
      tmp: "https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
    }
  }
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