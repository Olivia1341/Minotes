/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.micode.notes.gtask.exception;

/**
 * 网络操作失败自定义异常（受检异常）
 * 设计用途：封装网络请求失败时的错误信息
 * 典型应用场景：
 * 1. 同步任务时无法连接Google服务器
 * 2. API请求超时
 * 3. 服务器返回非200状态码
 */
public class NetworkFailureException extends Exception {
    // 序列化版本ID，保证反序列化兼容性
    private static final long serialVersionUID = 2107610287180234136L;

    /**
     * 空构造器 - 基础异常实例化
     * 示例：throw new NetworkFailureException();
     */
    public NetworkFailureException() {
        super();
    }

    /**
     * 带自定义消息的构造器 - 提供业务上下文
     * @param paramString 错误描述（如："API请求超时"）
     * 示例：throw new NetworkFailureException("无法连接同步服务器");
     */
    public NetworkFailureException(String paramString) {
        super(paramString);
    }

    /**
     * 完整异常链构造器 - 保留原始异常堆栈
     * @param paramString 业务错误描述
     * @param paramThrowable 原始异常（如IOException）
     * 示例：捕获IOException后抛出：
     * throw new NetworkFailureException("网络流异常", e);
     */
    public NetworkFailureException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }
}
