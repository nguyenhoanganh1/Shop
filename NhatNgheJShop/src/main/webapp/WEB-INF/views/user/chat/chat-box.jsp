<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="/static/css/chat.css" rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"
	integrity="sha512-RNLkV3d+aLtfcpEyFG8jRbnWHxUqVZozacROI4J2F1sTaDqo1dPQYs01OMi1t1w9Y2FdbSCDSQ2ZVdAC8bzgAg=="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/list.js/2.3.1/list.min.js"
	integrity="sha512-93wYgwrIFL+b+P3RvYxi/WUFRXXUDSLCT2JQk9zhVGXuS2mHl2axj6d+R6pP+gcU5isMHRj1u0oYE/mWyt/RjA=="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"
	integrity="sha512-hsqWiVBsPC5Hz9/hy5uQX6W6rEtBjtI8vyOAR4LAFpZAbQjJ43uIdmKsA7baQjM318sf8BBqrMkcWsfSsaWCNg=="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
	integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
	crossorigin="anonymous"></script>
	
<script src="/static/js/chat.js" type="text/javascript"></script>
<script src="/static/js/custom.js" type="text/javascript"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
	
<h3 class=" text-center" id="selectedUserId"></h3>
<div class="messaging">
	<div class="inbox_msg">
		<div class="inbox_people">
			
			<div class="headind_srch">
				<div class="recent_heading">
					<h4>Recent</h4>
				</div>
				<div class="srch_bar">
					<div class="stylish-input-group">
						<input id="userName" type="text" class="search-bar"
							placeholder="Search"> <span class="input-group-addon">
							<button type="button">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</span>
						<button onclick="fetchAll()" type="button">
							<i class="fa fa-refresh" aria-hidden="true"> Refresh</i>
						</button>
						<button onclick="registration()" type="button">
							<i class="fa fa-user" aria-hidden="true"> registration</i>
						</button>
					</div>
				</div>
			</div>

			<div class="inbox_chat">
				<div id="userList" class="chat_list"></div>
			</div>

		</div>
		

		<div class="mesgs">
			
			<div class="chat-history">
				<ul>
				
				</ul>
				<div class="incoming_msg">
					<div class="incoming_msg_img">
						<img src="https://ptetutorials.com/images/user-profile.png"
							alt="sunil">
					</div>
					<div class="received_msg">
						<div class="received_withd_msg">
							<p>Test which is a new approach to have all solutions</p>
							<span class="time_date"> 11:01 AM | June 9</span>
						</div>
					</div>
				</div>

				<div class="outgoing_msg">
					<div class="sent_msg">
						<p>Test which is a new approach to have all solutions</p>
						<span class="time_date"> 11:01 AM | June 9</span>
					</div>
				</div>

			</div>

			<div class="type_msg">
				<div class="input_msg_write">
				<textarea id="message-to-send" name="message-to-send" value="" placeholder="Type a message" class="form-control" rows="3"></textarea>
					<button id="sendBtn" class="msg_send_btn" type="button">
						<i class="fa fa-paper-plane-o" aria-hidden="true"></i> 
					</button>
				</div>
			</div>
		</div>

	</div>
	<p class="text-center top_spac">
		Design by <a target="_blank"
			href="https://www.linkedin.com/in/sunil-rajput-nattho-singh/">Sunil
			Rajput</a>
	</p>
</div>
<script id="message-template" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message-data align-right">
            <span class="message-data-time">{{time}}, Today</span> &nbsp; &nbsp;
            <span class="message-data-name">You</span> <i class="fa fa-circle me"></i>
        </div>
        <div class="message other-message float-right">
            {{messageOutput}}
        </div>
    </li>
</script>

<script id="message-response-template" type="text/x-handlebars-template">
    <li>
        <div class="message-data">
            <span class="message-data-name"><i class="fa fa-circle online"></i> {{userName}}</span>
            <span class="message-data-time">{{time}}, Today</span>
        </div>
        <div class="message my-message">
            {{response}}
        </div>
    </li>
</script>