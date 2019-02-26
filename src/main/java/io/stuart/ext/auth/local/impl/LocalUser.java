/*
 * Copyright 2019 Yang Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.stuart.ext.auth.local.impl;

import java.nio.charset.StandardCharsets;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;

public class LocalUser extends AbstractUser {

    // this authentication provider is unused, because there is not permission
    // in stuart system
    @SuppressWarnings("unused")
    private LocalAuthImpl authProvider;

    private String username;

    private JsonObject principal;

    public LocalUser() {
    }

    public LocalUser(LocalAuthImpl authProvider, String username) {
        this.authProvider = authProvider;
        this.username = username;
    }

    @Override
    public JsonObject principal() {
        if (principal == null) {
            principal = new JsonObject().put("username", username);
        }

        return principal;
    }

    @Override
    public void setAuthProvider(AuthProvider authProvider) {
        if (authProvider instanceof LocalAuthImpl) {
            this.authProvider = (LocalAuthImpl) authProvider;
        } else {
            throw new IllegalArgumentException("Not a LocalAuthImpl");
        }
    }

    @Override
    protected void doIsPermitted(String permission, Handler<AsyncResult<Boolean>> resultHandler) {
        // not permission in stuart system
        resultHandler.handle(Future.succeededFuture(true));
    }

    @Override
    public void writeToBuffer(Buffer buff) {
        super.writeToBuffer(buff);

        byte[] bytes = username.getBytes(StandardCharsets.UTF_8);
        buff.appendInt(bytes.length);
        buff.appendBytes(bytes);
    }

    @Override
    public int readFromBuffer(int pos, Buffer buffer) {
        pos = super.readFromBuffer(pos, buffer);

        int len = buffer.getInt(pos);
        pos += 4;
        byte[] bytes = buffer.getBytes(pos, pos + len);
        username = new String(bytes, StandardCharsets.UTF_8);
        pos += len;

        return pos;
    }

}
