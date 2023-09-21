<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
        <!-- Bootstrap core CSS -->
        <link href="/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="/css/style.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${recipient.username}</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">
                        <#if messages??>
                            <#list messages as message>
                                <#if message.senderId == recipient.userId>
                                    <li class="receive-msg float-left mb-2">
                                        <div class="sender-img">
                                            <img src="${recipient.profilePhoto}" class="float-left">
                                        </div>
                                        <div class="receive-msg-desc float-left ml-2">
                                            <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                                ${message.content}
                                            </p>
                                            <span class="receive-msg-time">
                                                ${recipient.username}, ${message.timestamp}
                                            </span>
                                        </div>
                                    </li>
                                <#else>
                                    <li class="send-msg float-right mb-2">
                                        <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                            ${message.content}
                                        </p>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>

                </div>
                <div class="col-md-12 msg-box border relative">
                            <form action="/messages/${recipient.userId}" method="post">
                            <input type="text" name="messageContent" class="border-0" placeholder=" Send message" />
                            <button type="submit" class="btn-send-message">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                                          <path stroke-linecap="round" stroke-linejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" />
                                                        </svg>
                                                        </button>
                                                        </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script src="/js/bootstrap.min.js"></script>
</body>
</html>