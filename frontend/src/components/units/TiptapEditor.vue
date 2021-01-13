<template>
  <div class="editor">
    <EditorMenuBar :editor="editor" v-slot="{ commands, isActive }">
      <v-toolbar dense>
        <!-- <v-overflow-btn
          :items="dropdown_font"
          label="Select font"
          hide-details
          class="pa-0"
        ></v-overflow-btn> -->

        <template v-if="$vuetify.breakpoint.mdAndUp">
          <!-- <v-divider vertical></v-divider>

          <v-overflow-btn
            :items="dropdown_edit"
            editable
            label="Select size"
            hide-details
            class="pa-0"
            overflow
          ></v-overflow-btn>

          <v-divider vertical></v-divider>

          <v-spacer></v-spacer> -->

          <v-btn-toggle
            v-model="toggle_multiple"
            color="primary"
            dense
            group
            multiple
          >
            <v-btn
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold" text>
              <v-icon>mdi-format-bold</v-icon>
            </v-btn>

            <v-btn
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic" text>
              <v-icon>mdi-format-italic</v-icon>
            </v-btn>

            <v-btn
            :class="{ 'is-active': isActive.underline() }"
            @click="commands.underline" text>
              <v-icon>mdi-format-underline</v-icon>
            </v-btn>

            
          </v-btn-toggle>

          <!-- image -->
          <v-btn text>
            <v-file-input
            id="imgUpload"
            hide-input
            :rules="imageRules"
            accept="image/png, image/jpeg, image/bmp"
            prepend-icon="image"
            @change="changeImage(commands.image)"
            >
            </v-file-input>
          </v-btn>

          <div class="mx-4"></div>

          <v-btn-toggle
            v-model="toggle_exclusive"
            color="primary"
            dense
            group
          >
            <v-btn :value="1" text>
              <v-icon>mdi-format-align-left</v-icon>
            </v-btn>

            <v-btn :value="2" text>
              <v-icon>mdi-format-align-center</v-icon>
            </v-btn>

            <v-btn :value="3" text>
              <v-icon>mdi-format-align-right</v-icon>
            </v-btn>

            <v-btn :value="4" text>
              <v-icon>mdi-format-align-justify</v-icon>
            </v-btn>
          </v-btn-toggle>
        </template>
      </v-toolbar>
    </EditorMenuBar>
    <EditorContent :editor="editor" class="editorContent" />
    <v-dialog
      v-model="inputImageInfo"
      max-width="800"
      persistent
    >
      <!-- @click:outside="moveRoute" -->
      <v-card>
        <v-card-title class="headline">Enter the information of this image</v-card-title>

        <div class="imageInfo">
          <v-img
            width="100%"
            :src="targetImageUrl"
          ></v-img>
          <span class="optionTitle">Source</span>
          <div>
              <v-text-field
              ref="source"
              label="Source"
              height="40px"
              full-width
              outlined
              required
              dense=true
              v-model="source"
              >
              </v-text-field>
          </div>
          <span class="optionTitle">Description</span>
          <div>
              <v-text-field
              ref="description"
              label="Description"
              height="40px"
              full-width
              outlined
              required
              dense=true
              v-model="description"
              >
              </v-text-field>
          </div>
        </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
          >
            <!-- @click="moveRoute" -->
            Save
          </v-btn>
          <v-btn
            color="red darken-1"
            text
          >
            <!-- @click="moveRoute" -->
            Cancel
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { Editor, EditorContent, EditorMenuBar } from 'tiptap'
import { Bold, Italic, Link, HardBreak, Heading, Image, Underline } from 'tiptap-extensions'

export default {
  components: {
    EditorContent,
    EditorMenuBar
  },
  data: () => ({
    editor: null,
    imageRules: [
      value => !value || value.size < 2000000 || 'Image size should be less than 2 MB!',
    ],
    inputImageInfo: false,
    targetImageUrl: 'https://picsum.photos/1920/1080?random',
    source: '',
    description: ''
  }),
  methods: {
    async changeImage(command) {
      const image = document.getElementById('imgUpload').files[0]
      const {status, imageUrl} = await this.$store.dispatch('reporters/uploadImage' , { image })

      if(status === 200) {
        // this.targetImageUrl = imageUrl
        // this.inputImageInfo = true
        command({ src: imageUrl, class: 'imageClass' })
      }
    }
  },
  mounted() {
    this.editor = new Editor({
      content: '<p>This is just a boring paragraph</p>',
      extensions: [
        new Bold(),
        new Italic(),
        new Link(),
        new HardBreak(),
        new Heading({ levels: [1, 2, 3] }),
        new Image(),
        new Underline(),
      ]
    })
  },
  beforeDestroy() {
    this.editor.destroy()
  }
}
</script>

<style scoped>
.imageInfo {
  width: 80%;
  margin: 10px auto;
}
</style>

<style>
.editor img {
  width: 80%;
  display: block;
  margin: 10px auto;
}

.editorContent > div {
  min-height: 800px;
}
</style>
