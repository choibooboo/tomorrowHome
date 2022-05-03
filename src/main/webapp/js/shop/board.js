/*
게시물 리스트 변경
*/
function changeQnaList(pageno, search_type, search_value){
	let params = {"pageno":pageno};
	if(search_value != ""){
		params.search_type = search_type;
		params.search_value = search_value;
	}
	$.ajax({
		url: "qna_list_rest",
		method: "post",
		data: params,
		dataType: "json",
		success:function(resultObj){
			console.log(resultObj);
			if(resultObj.errorCode > 0){
				let data = resultObj.data;
				let htmlBuffer = ``;
				$("#qna_total_count").html(`총 ${data.totRecordCount} 게시글`);
				if(data.totRecordCount != 0){
					data.itemList.forEach(function(qna, i){
						let fDate = formatDate(qna.q_date);
						let toDate = formatDate(new Date());
						htmlBuffer += `<tr>
		                                <th scope="row">${qna.q_no}</th>
		                                <td><a href="qna_view?q_no=${qna.q_no}&pageno=${data.pageMaker.curPage}&search_type=${search_type}&search_value=${search_value}">${qna.q_title}</a>`;
		                if(fDate == toDate){
							htmlBuffer += `&nbsp;&nbsp;<span class="badge badge-danger">new</span>`;
						}
	                    htmlBuffer += `</td>
		                                    <td>${qna.m_id}</td>
		                                    <td>${fDate}</td>
		                                    <td>${qna.q_count}</td>
		                                </tr>`;
					});
					$("#qna_list_tbody").html(htmlBuffer);
					let paginationBuffer = ``;
					if(data.pageMaker.prevPage > 0){
						paginationBuffer += `<li class="page-item">
			                                    <button class="page-link" onclick="changeQnaList(${data.pageMaker.prevPage}, '${search_type}', '${search_value}');"><i class="fa fa-angle-left" aria-hidden="true"></i></button>
			                               	 </li>`;
					}
					for(let no = data.pageMaker.blockBegin; no <= data.pageMaker.blockEnd; no++){
						if(data.pageMaker.curPage == no){
							paginationBuffer += `<li class="page-item active"><button class="page-link" href="#">${no}</button></li>`;
						}
						if(data.pageMaker.curPage != no){
							paginationBuffer += `<li class="page-item"><button class="page-link" onclick="changeQnaList(${no}, '${search_type}', '${search_value}');">${no}</button></li>`;
						}
					}
					if(data.pageMaker.curPage < data.pageMaker.totPage){
						paginationBuffer += `<li class="page-item">
						                        <button class="page-link" onclick="changeQnaList(${data.pageMaker.nextPage}, '${search_type}', '${search_value}');"><i class="fa fa-angle-right" aria-hidden="true"></i></button>
					                    	 </li>`;
					}
					$(".pagination.pagination-sm.justify-content-center").html(paginationBuffer);
					$(".qna_btn.write_form").attr("search_type", search_type);
					$(".qna_btn.write_form").attr("search_value", search_value);
					$(".qna_btn.write_form").attr("pageno", pageno);
				}else{
					htmlBuffer += `<td colspan="5" style="text-align:center">게시글이 존재하지 않습니다</td>`;
					$("#qna_list_tbody").html(htmlBuffer);
					$(".pagination.pagination-sm.justify-content-center").html("");
				}
			}else{
				Toast.fire({ icon: 'error', title: resultObj.errorMsg });
			}
		}
	});
}

/*
게시글 목록 이동
*/
$(".qna_btn.list").on("click", function(){ 
	let pageno = $(this).attr("pageno");
	let search_type = $(this).attr("search_type");
	let search_value = $(this).attr("search_value");
	location.href = `qna_list?pageno=${pageno}&search_type=${search_type}&search_value=${search_value}`;
});

/*
게시글 전체 리스트
*/
$(".qna_btn.list_all").on("click", function(){ 
	location.href = `qna_list`;
});

/*
게시글 검색
*/
$(".qna_btn.qna_search").on("click", function(){
	let search_type = $("#qna_search_sel").val();
	let search_value = $("#qna_search_txt").val().trim();
	let pageno = $(this).attr("pageno");
	sessionStorage.setItem("search_type", search_type);
	changeQnaList(pageno, search_type, search_value);
});

/*
게시글 삭제 
*/
$(".qna_btn.delete").on("click", function(){
	let pageno = $(this).attr("pageno");
	let q_no = $(this).attr("q_no");
	let search_type = $(this).attr("search_type");
	let search_value = $(this).attr("search_value");
	ToastConfirm.fire({ icon: 'question', 
						title: "게시글을 삭제하시겠습니까?\n 삭제 후 복구가 불가능합니다"}).then((result) => {
						if(result.isConfirmed){
							$.ajax({
								url: "qna_delete_rest",
								method: "post",
								data: {
										"q_no":q_no,
										"pageno":pageno
										},
								dataType: "json",
								success:function(resultObj){
									if(resultObj.errorCode > 0){
										Toast.fire({ icon: 'success', title: resultObj.errorMsg }).then((result) => {
												location.href = `qna_list?pageno=${pageno}&search_type=${search_type}&search_value=${search_value}`;
											});
									}else{
										Toast.fire({ icon: 'error', title: resultObj.errorMsg });
									}
								}
							});
						}
	});
});

/*
게시글 수정 폼 
*/
$(".qna_btn.update_form").on("click", function(){
	let q_no = $(this).attr("q_no");
	let pageno = $(this).attr("pageno");
	let search_type = $(this).attr("search_type");
	let search_value = $(this).attr("search_value");
	location.href = `qna_update_form?q_no=${q_no}&pageno=${pageno}&search_type=${search_type}&search_value=${search_value}`;
});

/* 
게시글 수정 
*/ 
$(".qna_btn.update").on("click", function(){ 
	if($("#q_title_txt").val() == "" || CKEDITOR.instances.q_content_area.getData() == ""){
		Toast.fire({ icon: 'warning', title: "필수 입력값을 입력하지 않았습니다.\n 제목과 내용을 모두 입력해주세요" });
		return;
	}
	ToastConfirm.fire({ icon: 'question', 
						title: "게시글을 수정하시겠습니까?"}).then((result) => {
						if(result.isConfirmed){
							$("#qna_update_form").attr("action", "qna_update"); 
							$("#qna_update_form").submit(); // q_no, q_titla, q_content 
						}
				});
});

/*
새글 등록 폼 
*/
$(".qna_btn.write_form").on("click", function(){
	let pageno = $(this).attr("pageno");
	let search_type = $(this).attr("search_type");
	let search_value = $(this).attr("search_value");
	location.href = `qna_write_form?pageno=${pageno}&search_type=${search_type}&search_value=${search_value}`;
});

/*
답글 등록 폼 
*/
$(".qna_btn.reply").on("click", function(){
	let pageno = $(this).attr("pageno");
	let q_no = $(this).attr("q_no");
	let search_type = $(this).attr("search_type");
	let search_value = $(this).attr("search_value");
	location.href = `qna_reply_form?pageno=${pageno}&q_no=${q_no}&search_type=${search_type}&search_value=${search_value}`;
});

/*
게시글 등록
*/
$(".qna_btn.new_write").on("click", function(){
	if($("#q_title_txt").val() == "" || CKEDITOR.instances.q_content_area.getData() == ""){
		Toast.fire({ icon: 'warning', title: "필수 입력값을 입력하지 않았습니다.\n 제목과 내용을 모두 입력해주세요" });
		return;
	}
	$("#qna_write_form").attr("action", "qna_new_write");
	$("#qna_write_form").submit();
});

/*
답글 등록
*/
$(".qna_btn.reply_write").on("click", function(){
	if($("#q_title_txt").val() == "" || CKEDITOR.instances.q_content_area.getData() == ""){
		Toast.fire({ icon: 'warning', title: "필수 입력값을 입력하지 않았습니다.\n 제목과 내용을 모두 입력해주세요" });
		return;
	}
	$("#qna_reply_write_form").attr("action", "qna_reply_write");
	$("#qna_reply_write_form").submit();
});

/*
ckeditor
*/
$(() => {
	if($("#q_content_area").length != 0){
		 CKEDITOR.replace('q_content_area', {
						height: 500                                                  
                 	});
	}
	if($("#qna_search_sel").length != 0){
		let search_type = sessionStorage.getItem("search_type");
		if(search_type){
			$(".nice-select .current").html($(`.list > .option[data-value='${search_type}']`).html());
			$(`#qna_search_sel > option[value='${search_type}']`).attr("selected", true);
		}
	}
});

