<template>
    <div class="writeArticle">
        <h1 class="title">Post Article</h1>
        <div class="subTitleAndBtn">
            <h2 class="subTitle">Post form</h2>
            <v-btn
            color="indigo"
            dark
            >
                Post
            </v-btn>
        </div>
        <v-divider></v-divider>

        <div class="articleTitle">
            <span>Title</span>
            <div class="optionContent">
                <v-text-field
                ref="title"
                label="title"
                height="40px"
                full-width
                outlined
                required
                dense=true
                >
                </v-text-field>
            </div>
        </div>
        <div class="articleSubTitle">
            <span>Subtitle</span>
                <div>
                <v-text-field
                ref="subTitle"
                label="subTitle"
                height="40px"
                full-width
                outlined
                required
                dense=true
                >
                </v-text-field>
            </div>
        </div>
        <div class="category">
            <span>Section</span>
            <v-select
                :items="categoryNames"
                label="Category"
                solo
                height="40px"
                dense="true"
                v-model="category"
            ></v-select>
        </div>
        <div class="mainImageContainer">
            <span>Main Image</span>
            <v-btn @click="mainImageForm.show = true" text>
              <v-icon>mdi-camera</v-icon>
            </v-btn>
            <v-file-input
                id="mainImage"
                ref="mainImage"
                class="displayNone"
                :rules="imageRules"
                hide-input
                accept="image/png, image/jpeg, image/bmp"
                @change="changeImage"
            ></v-file-input>
            <span>{{ mainImageForm.fileName }}</span>
        </div>

        <div class="editorContainer">
            <TiptapEditor @giveEditor="getEditor" />
        </div>
        <v-dialog
        v-model="mainImageForm.show"
        max-width="800"
        >
        <!-- @click:outside="moveRoute" -->
        <v-card>
            <v-card-title class="headline">Main Image</v-card-title>

            <div class="imageContainer">
                <div v-if="mainImageForm.imageUrl">
                    <v-img
                        width="100%"
                        :src="mainImageForm.imageUrl"
                    ></v-img>
                </div>
                <div v-if="!mainImageForm.imageUrl" style="text-align: center;padding: 20px ">
                    Upload the main Image
                </div>
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
                    v-model="mainImageForm.source"
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
                    v-model="mainImageForm.description"
                    >
                    </v-text-field>
                </div>
            </div>
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="green darken-1"
                text
                @click="selectImage"
            >
                Upload
            </v-btn>
            <v-btn
                color="red darken-1"
                text
                @click="mainImageForm.show = false"
            >
                Close
            </v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
</template>

<script>
import TiptapEditor from '../units/TiptapEditor'

export default {
    components: {
        TiptapEditor
    },
    data: () => ({
        categories: [],
        // categoryNames: ['Politics', 'Economy', 'Society', 'IT', 'Science', 'World', 'Sports'],
        categoryNames: [],
        category: '',
        imageRules: [
            value => !value || value.size < 2000000 || 'Image size should be less than 2 MB!',
        ],
        mainImageForm: {
            show: false,
            fileName: '',
            // imageUrl: 'https://picsum.photos/1920/1080?random',
            imageUrl: '',
            source: '',
            description: ''
        },
        editor: null
    }),
    methods: {
        selectImage() {
            document.getElementById('mainImage').click()
        },
        async changeImage(image) {
            const {status, imageUrl} = await this.$store.dispatch('reporters/uploadImage' , { image })

            if(status === 200) {
                this.mainImageForm.imageUrl = imageUrl
            }
        },
        getEditor(editor) {
            console.log('getEditor')
            console.log(editor)
            this.editor = editor
        }
    },
    async created() {
        const { status, categories } = await this.$store.dispatch('article/getCategory')
        
        if(status === 200) {
            const categoryNames = []
            categories.forEach(category => {
                categoryNames.push(category.name)
            });
            this.categories = categories
            this.categoryNames = categoryNames
            this.category = this.categoryNames[0]
        }
    }
}           
</script>

<style scopre>

.title{
    font-weight: 300;
    font-size: 50px;
    line-height: 56px;
    margin-bottom: 24px;
}

.subTitleAndBtn {
    height: 50px;
    display: grid;
    grid-template-columns: 100px 100px;
    justify-content: space-between;
    align-content: center;
}

.subTitle{
    font-weight: 700;
    font-size: 17px;
    display: grid;
    align-items: center;
}


.articleTitle {
    padding-top: 15px;
}

.category {

}

.formContainer{
    width: 100%;
    height: 600px;
    border: 1px solid black;
}

.editorContainer {
    width: 100%;
}

.mainImageContainer {
    width: 200px;
    padding-bottom: 20px;
    display: grid;
    grid-template-columns: 100px 50px 1fr;
    align-items: center;
}

.mainImage {
    text-align: center;
}

.imageContainer {
  width: 80%;
  margin: 10px auto;
}

.displayNone{
    display: none;
}

</style>