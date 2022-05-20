
<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*" %>
<%@ page import="com.shop.DishShop.*" %>

<html>

	<head>
		<title> Jim's Dishes </title>
	</head>
<% SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy"); %>
	<body>
		<h1>Welcome to Jim's Dish Shop!  </h1>
		<i>Please, select the dishes you 're considering to buy</i>
		<br>
		<br>
	<%
	Shop s= new Shop();
	Basket b= new Basket();
	for(int i=0;i<s.get_item_count();i++){
		out.println(s.get_item(i));
		
	%>
	  	<button onclick="add_item(<%=i%>)" type="button">+</button>
	  	<button onclick="remove_item(<%=i%>)" type="button">-</button>
		<br><br>			
	<%}%>
	<div id="sum"> <% out.print(b);%> </div>
	<script>

	var id=[];
	function add_item(let i) {
		<%
			b.add_Dish(s.get_item(i));
			out.println(b);
		%>
		<div id="sum"> <% out.print(b);%> </div>
	}
	function remove_item(let i) {
		<%
			b.remove_Dish(s.get_item(i));
		%>
		<div id="sum"> <% out.print(b);%> </div>
	}
</script>
	
	</body>
	
	



</html>