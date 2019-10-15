How run it?

1. Config Server
2. Registry Service
3. Gateway Service
4. Auth-Center
4. Client


GET TOKEN: 

<pre>curl -i -H "Content-Type: application/json" -X POST -d '{"username":"USER","PASSWORD":"shuaicj"}' http://localhost:8080/login</pre>

You will see the token in response header for user. Note that the status code 401 will be returned if you provide incorrect username or password. And similarly, get token for user admin:

<pre>curl -i -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"admin"}' http://localhost:8080/login</pre>

The user admin is defined with two roles: USER and ADMIN

The general command to verify if the auth works is as follows:

<pre>curl -i -H "Authorization: Bearer TOKEN" http://localhost:8080/backend/user</pre>

|                                     | /housing-client/admin | /housing-client/user | /housing-client/guest |
| ----------------------------------- | -------------- | ------------- | -------------- |
| `admin` token (role `USER` `ADMIN`) |            200 |           200 |            200 |
| `user` token (role `USER`)       |            403 |           200 |            200 |
| no token                            |            401 |           401 |            200 |