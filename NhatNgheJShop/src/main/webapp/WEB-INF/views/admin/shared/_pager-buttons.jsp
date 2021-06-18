<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="btn-group btn-group-sm">
    <a href="${url}/index/0" class="btn btn-warning">
        <i class="glyphicon glyphicon-hand-up"></i>
    </a>
    <a href="${url}/index/${pager.pageNo - 1}" class="btn btn-info">
        <i class="glyphicon glyphicon-hand-left"></i>
    </a>
    <a class="btn btn-success">${pager.pageNo + 1}/${pager.pageCount} </a>
    <a href="${url}/index/${pager.pageNo + 1}" class="btn btn-info">
        <i class="glyphicon glyphicon-hand-right"></i>
    </a>
    <a href="${url}/index/${pager.pageCount - 1}" class="btn btn-warning">
        <i class="glyphicon glyphicon-hand-down"></i>
    </a>
</div>