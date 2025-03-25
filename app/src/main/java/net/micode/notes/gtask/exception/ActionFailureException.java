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

/*
 * GTasks操作失败自定义异常（运行时异常）
 * 设计用途：封装同步动作失败时的具体错误信息
 * 典型应用场景：
 * 1. 创建/更新任务时JSON序列化失败
 * 2. 远程API调用返回非预期数据
 * 3. 本地数据库操作异常
 */
public class ActionFailureException extends RuntimeException {
    // 序列化版本ID，保证反序列化兼容性
    // 即使未显式使用序列化，保持良好实践
    private static final long serialVersionUID = 4425249765923293627L;

    /**
     * 空构造器 - 用于不需要额外描述信息的异常抛出
     * 示例：throw new ActionFailureException();
     */
    public ActionFailureException() {
        super();
    }

    /**
     * 带自定义消息的构造器 - 提供业务上下文描述
     * @param paramString 自定义错误信息（如："无法生成任务创建JSON"）
     * 示例：throw new ActionFailureException("同步元数据损坏");
     */
    public ActionFailureException(String paramString) {
        super(paramString);
    }

    /**
     * 完整异常链构造器 - 保留原始异常堆栈
     * @param paramString 业务错误描述
     * @param paramThrowable 原始异常对象（如JSONException）
     * 示例：捕获JSONException后抛出：
     * throw new ActionFailureException("解析响应失败", e);
     */
    public ActionFailureException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }
}
