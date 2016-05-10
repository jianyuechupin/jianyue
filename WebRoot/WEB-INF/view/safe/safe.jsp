<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="container">
	<table id="table" data-toggle="table" data-url="list"
		data-show-columns="true" data-search="false" data-show-refresh="true"
		data-show-toggle="true" data-pagination="true" data-height="400">
		<thead>
			<tr>
				<th data-field="uid" data-formatter="idFormatter">序号</th>
				<th data-field="dname">单位</th>
				<th data-field="uname">姓名</th>
				<th data-field="post">系统账号</th>
				<th data-field="sex">性别</th>
			</tr>
		</thead>
	</table>
</div>