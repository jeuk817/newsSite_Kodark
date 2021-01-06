<template>
   <div class="sidebar">
        <ul class="sidebar__menus">
            <a href="#">Account</a>
            <a href="#">Profile</a>
            <a href="#">Subscription List</a>
        </ul>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.sidebar{
    width: 320px;
    /* background-color: #f7f7f5; */
    /* position: fixed; */
    height: 100%;
}
.sidebar__menus{
    height: 300px;
    width: 190px;
    background-color: #f7f7f5;
    padding: 16px 0 16px 32px
}
.sidebar__menus > a:nth-child(1){
    font-size: 17px;
    font-weight: bold;
}
.sidebar a{
    display: flex;
    color: #4a4a4a;
    text-decoration: none;
    padding: 10px;
    font-weight: 600;
    font-size: 13px;
    margin-bottom: 5px;
}
</style>