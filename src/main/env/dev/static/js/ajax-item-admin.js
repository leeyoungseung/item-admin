/**
 * ajax-dev
 */
	var itemApi = "http://localhost:8080/api/items";
	
	$('#search-btn').on("click", function() {
		var itemNo = $("#search").val();
		getItemData(itemNo);
	});
	
	/* Get Item Data*/
	function getItemData(itemNo) {
		//alert(apiUrl+str);
		$.ajax({
			url: itemApi+'/'+itemNo,
			method : 'GET',
			success :  function(item){
				//alert(JSON.stringify(item))
				makeItemView(item);
			},
		    error: function(xhr, textStatus, error){
		      console.log(xhr.statusText);
		      console.log(textStatus);
		      console.log(error);
		    }
		});
	}
	
	function clearItemInfo() {
		$("#item-list").html('<div></div>');
	}
	
	/* Render Item Info */
	function makeItemView(item) {
		var view = '';
		view = view + '<div class="row justify-content-center">';
		view = view + '<div class="col-md-8 order-md-1">';
		view = view + '<h4 class="mb-3">Item Number : '+item.itemId+'</h4>';
		view = view + '	<form class="needs-validation" novalidate>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="itemName">ItemName</label> ';
		view = view + '			<input type="text"class="form-control" id="itemName" value="'+item.itemName+'" readonly>';
		view = view + '		</div>';
		view = view + '		<div class="mb-3">';
		view = view + '			<label for="itemDescription">itemDescription</label>';
		view = view + '			<div class="input-group">';
		view = view + '				<input type="text" class="form-control" id="itemDescription"  value="'+item.itemDescription+'" >';
		view = view + '			</div>';
		view = view + '		</div>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="makerCode">makerCode </label> ';
		view = view + '			<input type="text" class="form-control" id="makerCode"  value="'+item.makerCode+'">';
		view = view + '		</div>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="price">price</label> ';
		view = view + '			<input type="text" class="form-control" id="price" value="'+item.price+'">';
		view = view + '		</div>';
		view = view + '		<div class="mb-3">';
		view = view + '			<label for="saleStatus">saleStatus</label>';
		view = view + '			<div class="input-group">';
		
		var stopChecked = '';
		var startChecked = '';
		if (item.saleStatus === 0) {
			stopChecked = ' checked=checked'
		} else if (item.saleStatus === 1) {
			startChecked = ' checked=checked'
		}
		
		view = view + '				<div class="form-check form-check-inline">';
		view = view + '					<input class="form-check-input" type="radio" name="saleStatus" id="inlineRadio1" value="0" '+stopChecked+'>';
		view = view + '					<label class="form-check-label" for="inlineRadio1">판매정지</label>';
		view = view + '				</div>';
		view = view + '				<div class="form-check form-check-inline">';
		view = view + '					<input class="form-check-input" type="radio" name="saleStatus" id="inlineRadio2" value="1" '+startChecked+'>';
		view = view + '					<label class="form-check-label" for="inlineRadio2">판매중</label>';
		view = view + '				</div>';
		view = view + '			</div>';
		view = view + '		</div>';
		// hidden value
		view = view + '<input type="hidden" id="itemId" name="itemId" value="'+item.itemId+'">';
		view = view + '<input type="hidden" id="old_itemName" name="old_itemName" value="'+item.itemName+'">';
		view = view + '<input type="hidden" id="old_itemDescription" name="old_itemDescription" value="'+item.itemDescription+'">';
		view = view + '<input type="hidden" id="old_makerCode" name="old_makerCode" value="'+item.makerCode+'">';
		view = view + '<input type="hidden" id="old_price" name="old_price" value="'+item.price+'">';
		view = view + '<input type="hidden" id="old_saleStatus" name="old_saleStatus" value="'+item.saleStatus+'">';
		
		view = view + '	</form>';
		view = view + '</div>';
		view = view + '</div>';
		view = view + '<div class="row form-row float-right mx-auto">';
		view = view + '<button class="btn float-left btn-warning mr-3" type="button" id="update-btn">Item Update</button>';
		view = view + '<button class="btn float-right btn-danger mr-3" type="button" id="delete-btn">Item Delete</button>';
		view = view + '<button class="btn float-right btn-primary mr-3" type="button" id="clear-btn">Clear</button>';
		view = view + '</div>';
		
		$("#item-list").html(view);
	}
	
	/* Clear Item Info */
	$(document).on("click", '#clear-btn',function() {
		clearItemInfo();
	});
	
	
	/* Item Create Start*/
	$('#create-form-btn').on("click", function() {
		makeItemFormView();
	});
	
	/* Render Item Create Form */
	function makeItemFormView() {
		var view = '';
		view = view + '<div class="row justify-content-center">';
		view = view + '<div class="col-md-8 order-md-1">';
		view = view + '<h4 class="mb-3">Please Input Item Infomation </h4>';
		view = view + '	<form class="needs-validation" novalidate>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="itemName">ItemName</label> ';
		view = view + '			<input type="text"class="form-control" id="itemName">';
		view = view + '		</div>';
		view = view + '		<div class="mb-3">';
		view = view + '			<label for="itemDescription">itemDescription</label>';
		view = view + '			<div class="input-group">';
		view = view + '				<input type="text" class="form-control" id="itemDescription">';
		view = view + '			</div>';
		view = view + '		</div>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="makerCode">makerCode </label> ';
		view = view + '			<input type="text" class="form-control" id="makerCode">';
		view = view + '		</div>';
		view = view + '		<div class="row col-md-6 mb-3">';
		view = view + '			<label for="price">price</label> ';
		view = view + '			<input type="text" class="form-control" id="price">';
		view = view + '		</div>';
		view = view + '		<div class="mb-3">';
		view = view + '			<label for="saleStatus">saleStatus</label>';
		view = view + '			<div class="input-group">';
		view = view + '				<div class="form-check form-check-inline">';
		view = view + '					<input class="form-check-input" type="radio" name="saleStatus" id="inlineRadio1" value="0" checked=checked>';
		view = view + '					<label class="form-check-label" for="inlineRadio1">판매정지</label>';
		view = view + '				</div>';
		view = view + '				<div class="form-check form-check-inline">';
		view = view + '					<input class="form-check-input" type="radio" name="saleStatus" id="inlineRadio2" value="1">';
		view = view + '					<label class="form-check-label" for="inlineRadio2">판매중</label>';
		view = view + '				</div>';
		view = view + '			</div>';
		view = view + '		</div>';
		view = view + '	</form>';
		view = view + '</div>';
		view = view + '</div>';
		view = view + '<div class="row form-row float-right mx-auto">';
		view = view + '<button class="btn float-left btn-warning mr-3" type="button" id="create-process-btn">Create Item </button>';
		view = view + '<button class="btn float-right btn-danger mr-3" type="button" id="create-cancel-btn">Cancel </button>';
		view = view + '</div>';
		
		$("#item-list").html(view);
	}
	
	$(document).on("click", '#create-process-btn' ,function() {
		var itemName = $("#itemName").val();
		var itemDescription = $("#itemDescription").val();
		var makerCode = $("#makerCode").val();
		var price = $("#price").val();
		var saleStatus = $('input[name="saleStatus"]:checked').val();
		
		var itemObj = {
				"itemName":itemName,
				"itemDescription":itemDescription,
				"makerCode":makerCode,
				"price":price,
				"saleStatus":saleStatus
		};
		
		postItemData(JSON.stringify(itemObj));
	});
	
	/* Post Item Data*/
	function postItemData(newItem) {
		$.ajax({
			url: itemApi,
			method : 'POST',
			contentType : 'application/json; charset=UTF-8',
			data: newItem,
			success :  function(res){
				alert(JSON.stringify(res));
				clearItemInfo();
			},
		    error: function(xhr, textStatus, error){
		      console.log(xhr.statusText);
		      console.log(textStatus);
		      console.log(error);
		      alert(error)
		    }
		});
	}
	
	$(document).on("click", '#create-cancel-btn',function() {
		clearItemInfo();
	});
	
	
	/* Item Update Start*/
	$(document).on("click", '#update-btn' ,function() {
		
		var itemId = $("#itemId").val();
		//var itemName = $("#itemName").val();
		var itemDescription = $("#itemDescription").val();
		var makerCode = $("#makerCode").val();
		var price = $("#price").val();
		var saleStatus = $('input[name="saleStatus"]:checked').val();
		
		// Is it change?
		//var old_itemName = $("#old_itemName").val();
		var old_itemDescription = $("#old_itemName").val();
		var old_makerCode = $("#old_itemName").val();
		var old_price = $("#old_itemName").val();
		var old_saleStatus = $("#old_saleStatus").val(); 
		
		if (itemName === old_itemName && 
			itemDescription === old_itemDescription &&
			makerCode === old_makerCode &&
			price === old_price && 
			saleStatus == old_saleStatus) {
			
			return false;
		}
		
		var itemObj = {
				//"itemName":itemName,
				"itemDescription":itemDescription,
				"makerCode":makerCode,
				"price":price,
				"saleStatus":saleStatus
		};
		
		putItemData(itemId, JSON.stringify(itemObj));
	});
	
	/* PUT Item Data*/
	function putItemData(itemId, updateItem) {
		$.ajax({
			url: itemApi +'/'+itemId,
			method : 'PUT',
			contentType : 'application/json; charset=UTF-8',
			data: updateItem,
			success :  function(res){
				alert("Success Item Info Update "+res.itemId)
				makeItemView(res);
			},
		    error: function(xhr, textStatus, error){
		      console.log(xhr.statusText);
		      console.log(textStatus);
		      console.log(error);
		      alert(error)
		    }
		});
	}
	
	/* Item Delete Start*/
	$(document).on("click", '#delete-btn',function() {
		var itemId = $("#itemId").val();
		var itemName = $("#itemName").val();
		var check = confirm('Do you want to delete Item info : [ '+itemName+' ]')
		
		if (!check) {
			alert('Item Delete Cancel');
		    return
		}
		
		deleteItemData(itemId);
		
	});
	
	
	/* PUT Item Data*/
	function deleteItemData(itemId) {
		$.ajax({
			url: itemApi +'/'+itemId,
			method : 'DELETE',
			success :  function(res){
				alert("Success Item Info Delete "+itemId)
				clearItemInfo();
			},
		    error: function(xhr, textStatus, error){
		      console.log(xhr.statusText);
		      console.log(textStatus);
		      console.log(error);
		      alert(error)
		    }
		});
	}

 