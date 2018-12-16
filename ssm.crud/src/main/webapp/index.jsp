<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- web路径: 
	 1.不以/开始的相对路径找资源，以当前路径为基准，经常容易出问题。
	 2.以/开始的相对路径找资源，以服务器的路径为标准(http://localhost:3306)
 -->
 <%
 	pageContext.setAttribute("APP_PATH",request.getContextPath());
 %>
 		<!-- 引入Jquery -->
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
		<!-- 引入样式 -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- 引入js -->
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head> 
<body>
<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工添加</h4>
      </div>
      <div class="modal-body">
		<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
			      	 <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			   <label class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
			    	<span class="help-block"></span>
			    </div>
			  </div>
			   <div class="form-group">
			   <label class="col-sm-2 control-label">gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
				  </label>
				  <label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
				  </label>
			    </div>
			  </div>
			  <div class="form-group">
			   <label class="col-sm-2 control-label">deptName</label>
			    <div class="col-sm-4">
			    	<!-- 部门提交部门id即可 -->
				   <select class="form-control" name="dId" id="dept_add_select"></select>
			    </div>
			  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>


<!-- 员工修改的模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工修改</h4>
      </div>
      <div class="modal-body">
		<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			     	<p class="form-control-static" id="empName_update_static"></p>
			    </div>
			  </div>
			  <div class="form-group">
			   <label class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@qq.com">
			    	<span class="help-block"></span>
			    </div>
			  </div>
			   <div class="form-group">
			   <label class="col-sm-2 control-label">gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
				  </label>
				  <label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
				  </label>
			    </div>
			  </div>
			  <div class="form-group">
			   <label class="col-sm-2 control-label">deptName</label>
			    <div class="col-sm-4">
			    	<!-- 部门提交部门id即可 -->
				   <select class="form-control" name="dId" id="dept_update_select"></select>
			    </div>
			  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
      </div>
    </div>
  </div>
</div>


	<!-- 搭建页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal-btn">新增</button>
				<button class="btn btn-danger" id="emp_delete_all-btn">删除</button>
			</div> 
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
					<tr>
						<th>
						 <input type="checkbox" id="check_all"/>
						</th>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字 -->
			<div class="col-md-6" id="page_info_area"></div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>		
	</div>
	<script type="text/javascript">
		//定义全局变量,总记录数total,当前页的数量size
		var total;
		var page_Num;
		var size;
		//页面加载完成以后直接发送ajax请求要到分页数据,默认回到首页
		$(function(){
			to_page(1);
		}) 
		 
		function to_page(pn)
		{
			$.ajax({
				url:"${APP_PATH}/emp",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//3.解析并显示分页条信息
					build_page_nav(result);
				}	
			});
		}
		
		
		//解析并显示员工数据
		function build_emps_table(result)
		{
			$("#emps_table tbody").empty();//清空
			//拿到所有的员工数据保存在emps
			var emps = result.extend.PageInfo.list;
			//jquery提供的遍历方法,$.each(遍历的对象,每次遍历回调的函数function(索引,当前的对象))
			$.each(emps,function(index,item){
				//alert(item.empName);
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var empIDTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender == "M"?"男":"女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				//动态创建编辑按钮和删除按钮
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
							  .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
							  .append("编辑");
				//为编辑按钮添加一个自定义的属性"edit_id",方便指定哪一行进行修改,传入当前行的员工id
				editBtn.attr("edit_id",item.empId);
				
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
				  .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
				  .append("删除");
				//为删除按钮添加一个自定义的属性"delete_id",方便指定哪一行进行修改,传入当前行的员工id
				delBtn.attr("delete_id",item.empId);
				
				var BtnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				//append()方法执行完成以后还是返回原来的元素,#emps_table为table的id
				 $("<tr></tr>").append(checkBoxTd).append(empIDTd).append(empNameTd)
				 .append(genderTd).append(emailTd).append(deptNameTd).append(BtnTd)
				 .appendTo("#emps_table tbody");
			});
		}
		
		//解析并显示分页信息
		function build_page_info(result)
		{
			$("#page_info_area").empty();//清空
			//page_info_area为分页信息的id，追加到该id中
			$("#page_info_area").append("当前"+result.extend.PageInfo.pageNum+"页,"+
			  "总"+result.extend.PageInfo.pages+"页,总共"+result.extend.PageInfo.total+"条记录");
			//将总记录数放到total中,方便新增员工的时候跳到最后一页
			total = result.extend.PageInfo.total;
			//将当前页放到page_Num中,方便修改员工信息的时候跳转到修改完的页面
			page_Num = result.extend.PageInfo.pageNum;
			//将当前页的数量放到size中,方便复选框项全被选中时全选按钮的选中状态也被选中
			size = result.extend.PageInfo.size;
		}
		
		//解析并显示分页条
		function build_page_nav(result)
		{
			$("#page_nav_area").empty();//清空
			//page_nav_area为分页条的id，所有元素追加到该id中(li>ul>nav>id)
			var nav = $("<nav></nav>");
			var ul = $("<ul></ul>").addClass("pagination");
			var FirstPageLi = $("<li></li>").append($("<a></a>").append("首页")); 
			var PrePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			//如果当前页没有上一页(即为第1页)就禁用首页按钮和上一页按钮
			if(result.extend.PageInfo.hasPreviousPage == false)
			{
				FirstPageLi.addClass("disabled");
				PrePageLi.addClass("disabled");
			}
			else
			{
				//为首页添加事件
				FirstPageLi.click(function(){
				to_page(1);
				});  
				//为上一页添加事件,判断是否会超过首页,超过就回到首页
				PrePageLi.click(function(){
					to_page(result.extend.PageInfo.pageNum-1>0?result.extend.PageInfo.pageNum-1:1);
				});
			}			
			var	NextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var LastPageLi = $("<li></li>").append($("<a></a>").append("尾页"));
			//如果当前页没有下一页(即为尾页)就禁用尾页按钮和下一页按钮
			if(result.extend.PageInfo.hasNextPage == false)
			{
				NextPageLi.addClass("disabled");
				LastPageLi.addClass("disabled");
			}
			else
			{
				//为下一页添加事件
				NextPageLi.click(function(){
					to_page(result.extend.PageInfo.pageNum+1>result.extend.PageInfo.pages?result.extend.PageInfo.pages:result.extend.PageInfo.pageNum+1);
				});
				//为尾页添加事件
				LastPageLi.click(function(){
					to_page(result.extend.PageInfo.pages);
				}); 
			} 
			//添加首页和前一页到ul中
			ul.append(FirstPageLi).append(PrePageLi);
			//遍历连续显示的页码号1，2，3，4，5...
			$.each(result.extend.PageInfo.navigatepageNums,function(index,item){
				var NumLi = $("<li></li>").append($("<a></a>").append(item));
				if(result.extend.PageInfo.pageNum == item)
				{
					NumLi.addClass("active");//当前页为活动active页
				}
				//为连续显示的每一页页码添加click()事件
				NumLi.click(function(){
					to_page(item);
				});
				//添加页码到ul中
				ul.append(NumLi);
			});
			//添加下一页和尾页到ul中
			ul.append(NextPageLi).append(LastPageLi);
			//添加ul到nav中
			nav.append(ul).appendTo("#page_nav_area");
		}
		
		//清空表单样式与内容
		function reset_form(ele)
		{
			//重置表单内容
			$(ele)[0].reset();
			//清空表单样式
			$(ele).find("*").removeClass("has-error has-success");
			//<span class="help-block"></span>
			$(ele).find(".help-block").text("");
		}
		
			//点击新增按钮显示下拉列表的部门信息并弹出模态框
		$("#emp_add_modal-btn").click(function(){
				//清空下拉列表的值
				$("#empAddModal select").empty();
		 		//1.表单的完整重置,包括内容和样式
		 		 reset_form("#empAddModal form");
		 		//2.发送ajax请求,查出部门信息,显示在下拉列表
		 		 $.ajax({
		 			 url:"${APP_PATH}/depts",
		 			 type:"GET",
		 			 success:function(result){
		 				// console.log(result);
		 				//用id找下拉列表的方法：$("#dept_add_select"),另外一种方法是模态框的id：empAddModal下只有一个下拉列表
		 				$.each(result.extend.depts,function(){
		 					//this是指result.extend.depts当前遍历的对象就是department对象
		 					var option = $("<option></option>").append(this.deptName).attr("value",this.deptId);
		 					option.appendTo("#empAddModal select");
		 				});
		 			 }
		 		 });	 		
		 		//3.弹出模态框
		 		$("#empAddModal").modal({
		 			backdrop:"static"
		 		});
		 	});
			
			//校验表单数据
			function validate_add_form()
			{
				//1.拿到要校验的数据,使用正则表达式
				var empName = $("#empName_add_input").val();
				var empName_regx = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
				if(!empName_regx.test(empName))
				{
					//alert("用户名可以是2-5位中文或6-16位英文和数字的组合！");
					show_validate_msg("#empName_add_input","error","用户名必须是2-5位中文或6-16位英文和数字的组合！");
					return false;
				}
				else
				{
					show_validate_msg("#empName_add_input","success","");
				}
				//2.校验邮箱
				var email = $("#email_add_input").val();
				var	email_regx = /(^[a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!email_regx.test(email))
				{
					show_validate_msg("#email_add_input","error","邮箱格式不正确！");
					return false;
				}
				else
				{
					show_validate_msg("#email_add_input","success","");
				}
				return true;
			}
			
			//显示校验信息传入3个参数(元素的id,是否成功,提示的信息)
			function show_validate_msg(ele,status,msg)
			{
				//清楚元素的校验状态和内容
				$(ele).parent().removeClass("has-success has-error");
				$(ele).next("span").text("");
				if(status == "success")
				{
					$(ele).parent().addClass("has-success");
					$(ele).next("span").text(msg);
				}
				else
				{
					$(ele).parent().addClass("has-error");
					$(ele).next("span").text(msg);
				}
			}
			
			//点击保存按钮，保存员工数据
			$("#emp_save_btn").click(function(){
				//注意：用户会绕过前端校验,所以要前端+后端双校验
				//1. 提交数据到服务器进行校验(前端校验)
				 if(!validate_add_form())
				{
					return false;
				} 
				//2.判断用户名的ajax请求是否成功,如果失败就直接返回false
				if($(this).attr("ajax_val") == "error")
				{
					return false;
				}
				//3.模态框中的填写的数据提交到服务器保存(发送ajax请求) 
				 $.ajax({
					url:"${APP_PATH}/emp",
					type:"POST",
					data:$("#empAddModal form").serialize(),
					success:function(result){
						//alert(result.msg);//保存成功
						//1.判断后端校验是否通过
						if(result.code == 100)
						{
							//2.关闭模态框
							$("#empAddModal").modal('hide');
							//3.来到最后一页,显示刚才的数据
						 	to_page(total);
						}
						else
						{
							//如果校验未通过,则显示后端校验失败信息,判断哪个字段信息错误就显示在哪个字符
							if(undefined != result.extend.error_field.email)
							{
								//显示邮箱的错误信息
								show_validate_msg("#email_add_input","error",result.extend.error_field.email);
							}
							if(undefined != result.extend.error_field.empName)
							{
								//显示员工姓名的错误信息
								show_validate_msg("#empName_add_input","error",result.extend.error_field.empName);
							}				
						}
					}
				}); 
			}); 
			
			//员工姓名栏的内容发送改变时发送ajax请求校验是否重复
			$("#empName_add_input").change(function(){
				//获取当前姓名栏的内容
				var empName = this.value;
				$.ajax({
					url:"${APP_PATH}/checkuser",
					data:"empName="+empName,
					type:"POST",
					success:function(result){
						if(result.code == 100)
						{
							show_validate_msg("#empName_add_input","success","当前用户名可用");
							//给保存按钮emp_save_btn添加一个自定义属性"ajax_val",值为success
							$("#emp_save_btn").attr("ajax_val","success");
						}
						else
						{
							show_validate_msg("#empName_add_input","error",result.extend.val_msg);
							//给保存按钮emp_save_btn添加一个自定义属性"ajax_val",值为error
							$("#emp_save_btn").attr("ajax_val","error");
						}
					}
				});
			});
			
			//绑不上click事件,因为我们是创建按钮之前就绑定了click事件,而该按钮还未存在
			//jquery的新版本没有live()方法,替代的是on()方法
			/* $(".edit_btn").click(function(){
				alert("?");
			}); */
			
			//"编辑"按钮按钮绑定事件
			$(document).on("click",".edit_btn",function(){
				//2.清空下拉列表的值
				$("#empUpdateModal select").empty();
				//3.把当前行的id($(this).attr("edit_id"))自定义添加到"更新"按钮#emp_update_btn的自定义属性"edit_id"中
 				$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
				//4.显示下拉列表的部门信息并弹出模态框
		 		 $.ajax({
		 			 url:"${APP_PATH}/depts",
		 			 type:"GET",
		 			 success:function(result){
		 				  // console.log(result);
		 				  //用id找下拉列表的方法：$("#dept_add_select"),另外一种方法是模态框的id：empAddModal下只有一个下拉列表
		 				  $.each(result.extend.depts,function(){
		 				  	//this是指result.extend.depts当前遍历的对象就是department对象
		 				  	var option = $("<option></option>").append(this.deptName).attr("value",this.deptId);
		 				  	option.appendTo("#empUpdateModal select");
		 				});
		 			}
		 		 });	 		
		 		//5.弹出模态框
				$("#empUpdateModal").modal({
					//点击背景模态框不会被关闭,只能点击关闭按钮或者右上角
		 			backdrop:"static" 
		 		});
				//6.发送ajax请求查出当前行信息,即员工信息,$(this).attr("edit_id")获取当前行的id
				getEmp($(this).attr("edit_id"));
			});
			
			
			//单个删除:"删除"按钮按钮绑定事件
			$(document).on("click",".delete_btn",function(){
				//1.获取删除行的员工名empName：$(this).parents("tr").find("td:eq(1)").text()
				var empName = $(this).parents("tr").find("td:eq(2)").text();
				//2.获取删除行的员工的id传给Controller控制器,empId：$(this).attr("delete_id")
				var empId = $(this).attr("delete_id");
				if(confirm("确认删除【"+empName+"】"))
				{
					//3.确认删除,发送ajax请求删除当前行的员工信息
					$.ajax({
						url:"${APP_PATH}/emp/"+empId, 
						type:"DELETE",
						success:function(result){
							//4.返回处理的结果！
							alert(result.msg);
							//5.回到当前页
							to_page(page_Num);
						}
					});				
				}
			});

				
			//根据当前行的"编辑"按钮的添加的自定义属性"edit_id"查出该对象的所有信息
			function getEmp(id)
			{
				$.ajax({
					url:"${APP_PATH}/emp/"+id,
					type:"GET",
					success:function(result){
						//获取编辑当前行的Employee对象：result.extend.emp
						var emp_Data = result.extend.emp;
						//获取到的empName显示在修改模态框的元素empName_update_static(<p>)中
						$("#empName_update_static").text(emp_Data.empName);
						//获取到的email显示在修改模态框的元素email_update_input(input type="text")中
						$("#email_update_input").val(emp_Data.email);
						//获取到的email显示在修改模态框的元素(input type="radio" name="gender")中
						$("#empUpdateModal input[name=gender]").val([emp_Data.gender]);
						//获取到的email显示在修改模态框的元素(input type="radio" name="gender")中
						$("#empUpdateModal select").val([emp_Data.dId]);
					} 
				});
			}
			
			//更新员工信息
			$("#emp_update_btn").click(function(){
				//1.验证邮箱是否合法
				var email = $("#email_update_input").val();
				var	email_regx = /(^[a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!email_regx.test(email))
				{
					show_validate_msg("#email_update_input","error","邮箱格式不正确！");
					return false;
				}
				else
				{
					show_validate_msg("#email_update_input","success","");
				}
				//2.发送ajax请求更新员工信息,serialize()序列化表单的内容
				$.ajax({
					url:"${APP_PATH}/emp_change/"+$(this).attr("edit_id"),
					type:"GET",
					data:$("#empUpdateModal form").serialize(),
					success:function(result){
						//alert(result.msg);
						//3.关闭模态框
						$("#empUpdateModal").modal('hide');
						//4.回到更新的页面
						to_page(page_Num);
					} 
				});
			});
			
			//为全选复选框绑定事件
			$("#check_all").click(function(){   
				//attr获取checked是undefined,dom原生元素的推荐用prop获取checked值
				//.check_item为class,#为id属性
				$(".check_item").prop("checked",$(this).prop("checked"));
			}); 
			
			//为每个复选框绑定事件,使用on()方法是因为复选框是动态生成的
			$(document).on("click",".check_item",function(){
				//判断当前页的数量是否与复选框项选中的数量相同,相同则选中全选复选框(size为当前页的记录数)
				var flag = $(".check_item:checked").length == size;	
				$("#check_all").prop("checked",flag);
			});
			
			//为批量删除按钮绑定事件
			$("#emp_delete_all-btn").click(function(){
				//1.遍历被复选框选中的员工名字empName显示给用户
				var empNames = "";
				var dele_ids = "";
				$.each($(".check_item:checked"),function(){
					//取出被复选框选择的员工名字empName,并用","分隔,如luofan,aaaaaa,bbbbbb
					empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
					//取出被复选框选择的id组,并用"-"分隔,如1-2-3
					dele_ids += $(this).parents("tr").find("td:eq(1)").text()+"-"; 
				});
				if(confirm("确认删除【"+empNames.substring(0,empNames.length-1)+"】吗？"))
				{
					//2.发送ajax请求进行批量删除,传过去的id以"-"分隔,如1-2-3
					$.ajax({
						url:"${APP_PATH}/emp/"+dele_ids.substring(0,dele_ids.length-1),
						type:"DELETE",
						success:function(result){
							//3.弹出处理的结果
							alert(result.msg);
							//4.回到删除的页面
							to_page(page_Num);
						}
					});		
				}			
			});
			
	</script> 
</body>
</html>