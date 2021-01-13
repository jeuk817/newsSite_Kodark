<template>
    <div class="writeArticle">
        <h1 class="title">Post Article</h1>
        <h2 class="subTitle">Post form</h2>

        <div class="articleTitle">
            <span class="optionTitle">Title</span>
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
            <span class="optionTitle">Subtitle</span>
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
            <span class="optionTitle">Section</span>
            <v-select
            :items="category"
            label="Category"
            solo
            height="40px"
            dense="true"
            ></v-select>
        </div>

        <div class="editorContainer">
            <TiptapEditor />
        </div>

    </div>
</template>

<script>
import TiptapEditor from '../units/TiptapEditor'

export default {
    components: {
        TiptapEditor
    },
     data: () => ({
      category: ['Foo', 'Bar', 'Fizz', 'Buzz']

    }),
}           
</script>

<style scopre>

.title{
    font-weight: 300;
    font-size: 50px;
    line-height: 56px;
    margin-bottom: 24px;
}
.subTitle{
    border-bottom: 1px solid #bdbdbd;
    font-weight: 700;
    font-size: 17px;
    margin-bottom: 9px;
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

</style>