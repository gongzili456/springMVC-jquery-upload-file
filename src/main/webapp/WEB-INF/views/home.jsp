<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>welcome upload file</h1>

	<input id="fileupload" type="file" name="files[]" data-url="upload"
		multiple>


	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"> <input type="submit"
			value="submit">
	</form>

	<script src="resources/jquery-1.8.3.min.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/jquery-upload-file/jquery.ui.widget.js"></script>
	<script src="resources/jquery-upload-file/jquery.iframe-transport.js"></script>
	<script src="resources/jquery-upload-file/jquery.fileupload.js"></script>

	<script type="text/javascript">
		$(function() {
			$('#fileupload').fileupload({
				dataType : 'json',
				done : function(e, data) {
					console.log('data : ', data);
				}
			});
		});
	</script>

</body>
</html>
