# hi-balance-yes

### Auth
- POST /oauth/token
```json
{
	"grant_type": "password",
	"client_id": "my-trusted-client",
	"client_secret": "secret",
	"username": "user",
	"password": "user"
}
```
### User
- GET /users
- POST /users/register
```json
{
    "username": "user",
    "password": "123456",
    "roles": [
        {
          "name": "USER"
        },
        {
          "name": "ACTUATOR"
        }
    ],
    "active": true
}
```