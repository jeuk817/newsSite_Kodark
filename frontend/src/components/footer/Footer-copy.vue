<template>
  <div class="footer">
      <ul class="footer__items">
        <li class="footer__version">
            <span>©</span> 
            <span>2020</span>
            <span> version 0.0.1</span>
        </li>
        <li>
            <p>Introduce</p>
        </li>
        <li>
            <a href="#"><p>Git Repository</p></a>
        </li>
        <li>
            <p>Help</p>
        </li>
      </ul>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    p, span, a{
        text-decoration: none;
        color: inherit;
        font-size: 12px;
        line-height: 40px;
        margin-bottom: 0;
    }
    a:hover{
        cursor: pointer;
    }
    ul{
        padding-left: 0;
    }
    li{
        list-style: none;
    }
    .footer{
        background: #f7f7f5;
        width: 1200px;
        border-radius: 5px;
        height: 40px;
        line-height: 40px;
       
    }
    .footer__items{
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        justify-items: center;
        align-items: center;
    }
    .footer__version span{
        padding: 5px;
    }
</style>
