
# The API for Email Sending

## Setup
Modify the MailService Provider's API URL and Key in `src/main/resources/application.properties`

## POST /sendEmail

This endpoint allow you send the plain text email.

## Request 

| *key* | *type* | *required* |
| ------ | ----------- | ------ | 
| to | array or email contact | Y | 
| sender | array or email contact  | Y |
| subject | array or email contact  | Y |
| content | array or email contact  | Y | 
| cc |  array or email contact | N | 
| bcc |  array or email contact | N |

 Email contact can be one of the following formats:
 ```
 {"name":"Johh name","email":"john@email.com"}
 ```
 or ignore the name
 ```
 {"email":"john@email.com"}
 ```
 or simply just the email string
 ```
 "john.com"
```

## Demo 

POST http://java-env.bm3tfm73sg.ap-southeast-2.elasticbeanstalk.com/sendEmail
```
{
	"sender":"test@test.com",
	"content":"Hello world",
	"subject":"Welcome from Lu",
	"toRecipients":["i@gfw.fail"]
}
```
