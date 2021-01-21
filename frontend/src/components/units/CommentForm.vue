<template>
  <div>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      commentRules: [
        v => !!v || 'Comment is required',
        v => v.length <= 250 || 'Comment length must be less than 250'
      ],
      inputComment: ''
    }
  },
  methods: {
    submitComment() {
      if(!this.$refs.comment.validate(true)) return
      const content = this.inputComment
      this.$emit('submitComment', content)
    },
    closeCommentForm() {
      this.$emit('closeCommentForm')
    }
  }
}
</script>

