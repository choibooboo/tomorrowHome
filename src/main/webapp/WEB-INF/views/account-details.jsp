<%@page import="com.itwill.tomorrowHome.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- include_common_top -->
	<jsp:include page="common/include_common_top.jsp"/>
    <!-- include_common_top -->
    <link rel="stylesheet" href="css/shop/member.css">

</head>

<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="spinner-grow" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>

    <!-- Header Area -->
  	<jsp:include page="common/include_common_header.jsp"/>
    <!-- Header Area End -->

    <!-- Breadcumb Area -->
    <div class="breadcumb_area">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <h5>My Account</h5>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index">Home</a></li>
                        <li class="breadcrumb-item active">My Account</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcumb Area -->

    <!-- My Account Area -->
    <section class="my-account-area section_padding_100_50">
        <div class="container">
            <div class="row">
				<div class="col-12 col-lg-3">
				    <div class="my-account-navigation mb-50">
				        <ul>
				            <li><a href="my_account">My Account</a></li>
				            <li class="active"><a href="#">Account Details</a></li>
				            <li><a href="order_list">Orders</a></li>
				            <li><a href="member_logout">Logout</a></li>
				        </ul>
				    </div>
				</div>
                <div class="col-12 col-lg-9">
                    <div class="my-account-content mb-50">
                        <h5 class="mb-3">Account Details</h5>


                        <form id="member_accountDetail_form" action="member_account_update_action" method="post">
                            <div class="row">
                                <div class="col-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="m_id">ID *</label>
                                        <input type="text" class="form-control" id="m_id" name="m_id" value="${sM_id}" readonly>
                                    </div>
                                </div>
                                
                                <div class="col-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="firstName">Name *</label>
                                        <input type="text" class="form-control" id="m_name" name="m_name" placeholder="이름" value="${sMember.m_name}">
                                    </div>
                                </div>
                              
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="emailAddress">Email Address *</label>
                                        <input type="email" class="form-control m_u_check" id="m_email" name="m_email" placeholder="이메일" value="${sMember.m_email}">
                                    </div>
                                </div>
                                
                                 <div class="col-12">
                                    <div class="form-group">
                                        <label for="Address">Address *</label>
                                        <input type="text" class="form-control address m_u_check" id="m_address" name="m_address"  placeholder="주소" value="${sMember.m_address}">
                                    </div>
                                </div>
                                
                                 <div class="col-12">
                                    <div class="form-group">
                                        <label for="Post">Post *</label><br>
                                        <input type="text" class="form-control postcode m_u_check" id="m_post" name="m_post" placeholder="우편번호" value="${sMember.m_post}">
                                    	<button type="button" class="btn btn-outline-primary mb-1 searchAddr">search</button>
                                    </div>
                                </div>
                                
                                 <div class="col-12">
                                    <div class="form-group">
                                        <label for="Phone">Phone *</label>
                                        <input type="text" class="form-control m_u_check phone_number" id="m_phone" name="m_phone" placeholder="전화번호 ( - 제외 입력해주세요)" value="${sMember.m_phone}">
                                    </div>
                                </div>
                                
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="currentPass">Password *</label>
                                        <input type="password" class="form-control m_u_check" name="m_password" id="currentPass" value="">
                                    </div>
                                </div>
                                
                                &nbsp;&nbsp;&nbsp; <span style="color:red"> ✔️ 비밀번호 변경을 원할 시 작성해주세요</span>
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="newPass">New Password</label>
                                        <input type="password" class="form-control" name="new_password" id="newPass" value="">
                                    </div>
                                </div>
                                
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="confirmPass">Confirm New Password</label>
                                        <input type="password" class="form-control" id="confirmPass" value="">
                                    </div>
                                </div>
                                
                                <div class="col-12">
                                    <button type="button" class="btn btn-primary" id="member_update_btn">Save Changes</button>
                                    <button type="button" class="btn btn-outline-primary mb-1" id="withdrawal_btn" style="float:right">Withdrawal</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- My Account Area -->

    <!-- Footer Area -->
 	<jsp:include page="common/include_common_bottom.jsp"/>
    <!-- Footer Area -->

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
	<jsp:include page="common/include_common_script.jsp"/>
	<script src="js/shop/member.js"></script>
	
</body>

</html>