/* 
멤버 로그인 
*/
$("#member_login_form").on("submit", function(e){
	let id = $("#id").val();
	let password = $("#password").val();
	if(id == "" || password == ""){
		Toast.fire({ icon: "warning", title: "ID, 비밀번호를 모두 입력해주세요"});
		e.preventDefault();
		return; 
	}
	$.ajax({ 
		url: "member_login_rest", 
		method: "post", 
		data: $("#member_login_form").serialize(), 
		dataType: "json", 
		success:function(resultObj){ 
			console.log(resultObj); 
			if(resultObj.errorCode > 0){ 
				Toast.fire({ icon: "success", title: `${resultObj.errorMsg}\n${id}님 반갑습니다 😊`}).then((result) => {
					location.href = "index";
				});
			} else {
				Toast.fire({ icon: "error", title: resultObj.errorMsg});
			}
		}
	});
	e.preventDefault();
});

/*
회원가입
*/
$('#member_register_form').on('submit',function(e){
	if(!member_valiidation()){
		$(this).find("[type='submit']").blur();
		e.preventDefault();
		return;
	}
	$.ajax({ 
		url: "member_register_rest", 
		method: "post", 
		data: $("#member_register_form").serialize(), 
		dataType: "json", 
		success:function(resultObj){ 
			console.log(resultObj); 
			if(resultObj.errorCode > 0){ 
				let id = $("#m_id").val();
				Toast.fire({ icon: "success", title: resultObj.errorMsg }).then((result) => {
					$(".m_check").each(function(i, v){
						$(v).val("");
					});
					$("#id").focus();
				});
			} else {
				Toast.fire({ icon: "error", title: resultObj.errorMsg }).then((result) => {
					$("#m_id").focus();
				});
			}
		}
	});
	e.preventDefault();
});

function member_valiidation(){
	let result = true;
	$(".m_check").each(function(i, v){
		if($(v).val() == ""){
			Toast.fire({ icon: 'warning', title: "필수 입력값을 입력하지 않았습니다.\n 모든 값을 입력해주세요." });
			result = false;
			return false;
		}
	});
	if(!result){
		return result;
	}
	let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/; 
	if(!regPhone.test($(".phone_number").val())){
		Toast.fire({ icon: 'warning', title: "휴대폰 번호 형식이 유효하지 않습니다" });
		$(".phone_number").val("");
		return false;
	}
	return true;
}

/*
회원정보 수정 
*/
$("#member_update_btn").on("click", function(e){
	if(!member_update_valiidation()){
		return false;
	}
	ToastConfirm.fire({ icon: 'question', 
		title: "회원정보를 변경 하시겠습니까?"}).then((result) => {
		if(result.isConfirmed){
			$.ajax({ 
				url: "member_account_update_rest", 
				method: "post", 
				data: $("#member_accountDetail_form").serialize(), 
				dataType: "json", 
				success:function(resultObj){ 
					console.log(resultObj); 
					if(resultObj.errorCode > 0){ 
						Toast.fire({ icon: "success", title: resultObj.errorMsg }).then((result) => {
							location.href = "account_details";
						});
					} else {
						Toast.fire({ icon: "error", title: resultObj.errorMsg });
					}
				}
			});
		}
	});
});

function member_update_valiidation(){
	let result = true;
	$(".m_u_check").each(function(i, v){
		if($(v).val() == ""){
			Toast.fire({ icon: 'warning', title: "필수 입력값을 입력하지 않았습니다." });
			result = false;
			return false;
		}
	});
	if(!result){
		return result;
	}
	let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/; 
	if(!regPhone.test($(".phone_number").val())){
		Toast.fire({ icon: 'warning', title: "휴대폰번호 형식이 유효하지 않습니다" });
		$(".phone_number").val("");
		return false;
	}
	// 비밀번호 변경
	let newPass = $("#newPass").val();
	let confirmPass = $("#confirmPass").val();
	if(newPass != "" || confirmPass != ""){
		if(newPass == ""){
			Toast.fire({ icon: 'warning', title: "변경할 비밀번호를 입력해주세요" });
			$("#newPass").focus();
			return false;
		}
		if(confirmPass == ""){
			Toast.fire({ icon: 'warning', title:"비밀번호 확인을 입력해주세요" });
			$("#confirmPass").focus();
			return false;
		}
		if(newPass != confirmPass){
			Toast.fire({ icon: 'warning', title:"새 비밀번호와 비밀번호 확인이 일치하지 않습니다" });
			$("#confirmPass").focus();
			return false;
		}
	}
	return result;
}

/*
회원탈퇴
*/
$("#withdrawal_btn").on("click", function(){
	ToastConfirm.fire({ icon: 'question', 
		title: "회원을 탈퇴하시겠습니까?"}).then((result) => {
		if(result.isConfirmed){
			let id = $("#m_id").val();
			$.ajax({ 
				url: "member_withdrawal_rest", 
				method: "post", 
				data: {
						"id": id,
						"password": $("#m_password").val()
					  },
				dataType: "json", 
				success:function(resultObj){ 
					console.log(resultObj); 
					if(resultObj.errorCode > 0){ 
						Toast.fire({ icon: "success", title: resultObj.errorMsg }).then((result) => {
							location.href = "index";
						});
					} else {
						Toast.fire({ icon: "error", title: resultObj.errorMsg });
					}
				}
			});
		}
	});
});