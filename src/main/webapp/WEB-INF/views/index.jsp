<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script async src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script async src="/js/ajax-item-admin.js"></script>
<link href="/webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<style type="text/css">
.container {
	max-width: 960px;
}

.form-control-borderless {
	border: none;
}

.form-control-borderless:hover, .form-control-borderless:active,
	.form-control-borderless:focus {
	border: none;
	outline: none;
	box-shadow: none;
}

#item-list {
	border-style: outset;
}
</style>
</head>
<body class="bg-light">

	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4"
				src="/img/item-logo.png"
				alt="" width="72" height="72">
			<h1>Item Admin Page</h1>
			<p class="lead">상품 검색, 등록, 정보수정, 삭제등의 관리를 할 수 있습니다.</p>
		</div>


		<div class="row justify-content-center">
			<div class="col-12 col-md-10 col-lg-8">
				<form class="card card-sm">
					<div class="card-body row no-gutters align-items-center">
						<div class="col-auto">
							<i class="fas fa-search h4 text-body"></i>
						</div>
						<div class="col">
							<input
								class="form-control form-control-lg form-control-borderless"
								type="search"
								placeholder="Please Iuput Item Number And Click Search Button!" id="search">
						</div>
						<div class="col-auto">
							<button class="btn btn-lg btn-success" type="button" id="search-btn" >Search</button>
						</div>
					</div>
				</form>
			</div>
			<!--end of col-->
			<div class="card-body row no-gutters align-items-center">
				<button class="btn btn-lg btn-info" type="button" id="create-form-btn">Apply Item Info</button>
			</div>
		</div>

		<br />

		<div id="item-list">

		</div>


	</div>
	<!-- .container -->



</body>


</html>