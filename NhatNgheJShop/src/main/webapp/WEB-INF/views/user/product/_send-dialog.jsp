<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
				<button class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Chia sẽ cho người thân</h4>
			</div>
			<div class="modal-body">
				<div class="form-group colp-sm-6">
					<label>Người gửi</label> <input id="sender"
						placeholder="Sender email?" class="form-control">
				</div>
				<div class="form-group colp-sm-6">
					<label>Người nhận</label> <input id="receiver"
						placeholder="Receiver email?" class="form-control">
				</div>
				<div class="form-group colp-sm-6">
					<label>Tiêu đề</label> <input id="subject" placeholder="Subject?"
						class="form-control">
				</div>
				<div class="form-group colp-sm-6">
					<label>Nội dung</label>
					<textarea id="content" placeholder="Content?" class="form-control"></textarea>
				</div>
			</div>

			<div class="modal-footer">
				<div id="loading" style="display: none" class="pull-left">
					<img alt="" src="/static/images/loading.gif" width="20px"
						height="20px"> Vui lòng đợi trong giấy lát
				</div>
				<button class="btn btn-default btn-success btn-send"
					data-dismiss="modal">Gửi</button>
				<button class="btn btn-default btn-danger" data-dismiss="modal">Close</button>
			</div>
      </div>
      
    </div>
  </div>	
<div class="modal fade" id="send-dialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			
		</div>
	</div>
</div>