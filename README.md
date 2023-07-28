# getting-started
This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## OpenID Connect authorization code flow mechanism for protecting web applications
https://quarkus.io/guides/security-oidc-code-flow-authentication

https://es.quarkus.io/guides/security-openid-connect-dev-services

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```


## Access Keycloak
Go to development dashboard at http://localhost:8080/q/dev/ and click OIDC Provider (http://localhost:8080/q/dev/io.quarkus.quarkus-oidc/provider)
This keycloak is already configured for your development and test environments.

## GET /api/users/me
Perform a GET request against `/api/users/me` using `alice` user. In keycloak, Alice was created with admin and user roles.
http://localhost:32798/admin/master/console/#/quarkus/users/ca2214b6-e624-4bb9-93dd-81a9475a2630/settings

To get alice password to perform the request: http://localhost:32798/admin/master/console/#/quarkus/users/ca2214b6-e624-4bb9-93dd-81a9475a2630/credentials

```shell script
export access_token=$(\
    curl --insecure -X POST http://localhost:32798/realms/quarkus/protocol/openid-connect/token \
    --user backend-service:secret \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alice&password=alice&grant_type=password' | jq --raw-output '.access_token' \
 )
```

Using the access_token, do the request and it should return 200 because Alice has user role.
```shell script
curl -v -X GET \
  http://localhost:8080/api/users/me \
  -H "Authorization: Bearer "$access_token
```

Using the access_token, do the request and it should return 403 because Alice does not have superuser role
```shell script
curl -v -X GET \
  http://localhost:8080/api/users/me \
  -H "Authorization: Bearer "$access_token
```

For roles please login on keycloak and use the quarkus realm.
http://localhost:8080/q/dev/io.quarkus.quarkus-oidc/provider