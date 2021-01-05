<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8"></script> 
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<!-- SmartEditor2 --> 
	<div class="jsx-2303464893 editor"> 
		<div class="fr-box fr-basic fr-top" role="application"> 
			<div class="fr-wrapper show-placeholder" dir="auto" style="overflow: scroll;"> 
				<textarea name="notice_content" id="smartEditor" style="width: 100%; height: 412px;"></textarea>
	 		</div>
	  	</div>
	</div>



</body>
</html>
<script type="text/javascript">
var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		 oAppRef : oEditors,
		 elPlaceHolder : "smartEditor", 
		sSkinURI : "se2/SmartEditor2Skin.html", 
		fCreator : "createSEditor2",
		htParams : { 
		bUseToolbar : true, 
		bUseVerticalResizer : false, 
		bUseModeChanger : false }
	 }); 

	 $(function() {
		  $("#savebutton").click(function() { 
			  oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

				var selcatd = $("#selcatd > option:selected").val();
				var title = $("#title").val();
				var content = document.getElementById("smartEditor").value;

				if (selcatd == "") { alert("카테고리를 선택해주세요."); return; }
				if (title == null || title == "") {
					 alert("제목을 입력해주세요."); 
					$("#title").focus(); return; 
					}
				if(content == "" || content == null || content == '&nbsp;' ||
				 content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
					 alert("본문을 작성해주세요."); oEditors.getById["smartEditor"].exec("FOCUS");


				var result = confirm("발행 하시겠습니까?");

				if(result){ alert("발행 완료!"); $("#noticeWriteForm").submit();
				}else{ 
					return;
					 } 
				 }
			});
		})

</script>
