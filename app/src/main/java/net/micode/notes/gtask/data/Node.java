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

package net.micode.notes.gtask.data;

import android.database.Cursor;

import org.json.JSONObject;

/*
 * 抽象类 Node，作为数据同步和操作的基类。
 * 定义了数据节点的基本属性和抽象方法，子类需要实现这些方法以完成具体的数据操作和同步逻辑。
 */
public abstract class Node {
    // 以下为同步操作类型
    // 无操作
    public static final int SYNC_ACTION_NONE = 0;
    // 在远程添加
    public static final int SYNC_ACTION_ADD_REMOTE = 1;
    // 在本地添加
    public static final int SYNC_ACTION_ADD_LOCAL = 2;
    // 在远程删除
    public static final int SYNC_ACTION_DEL_REMOTE = 3;
    // 在本地删除
    public static final int SYNC_ACTION_DEL_LOCAL = 4;
    // 在远程更新
    public static final int SYNC_ACTION_UPDATE_REMOTE = 5;
    // 在本地更新
    public static final int SYNC_ACTION_UPDATE_LOCAL = 6;
    // 更新冲突
    public static final int SYNC_ACTION_UPDATE_CONFLICT = 7;
    // 错误
    public static final int SYNC_ACTION_ERROR = 8;

    // 以下为任务相关参数
    // 任务的全局唯一标识符
    private String mGid;
    // 任务名称
    private String mName;
    // 最后修改时间
    private long mLastModified;
    // 标记是否删除
    private boolean mDeleted;

    // 构造方法，初始化节点的基本属性。
    public Node() {
        mGid = null;
        mName = "";
        mLastModified = 0;
        mDeleted = false;
    }

    // 获取创建操作的JSON对象。
    public abstract JSONObject getCreateAction(int actionId);

    // 获取更新操作的JSON对象。
    public abstract JSONObject getUpdateAction(int actionId);

    // 根据远程JSON数据设置内容。
    public abstract void setContentByRemoteJSON(JSONObject js);

    // 根据本地JSON数据设置内容
    public abstract void setContentByLocalJSON(JSONObject js);

    // 根据内容生成本地JSON对象
    public abstract JSONObject getLocalJSONFromContent();

    // 获取同步操作类型
    public abstract int getSyncAction(Cursor c);

    // 设置任务的全局唯一标识符
    public void setGid(String gid) {
        this.mGid = gid;
    }

    // 设置任务名称
    public void setName(String name) {
        this.mName = name;
    }

    // 设置任务的最后修改时间
    public void setLastModified(long lastModified) {
        this.mLastModified = lastModified;
    }

    // 设置任务的删除标记
    public void setDeleted(boolean deleted) {
        this.mDeleted = deleted;
    }

    // 获取任务的全局唯一标识
    public String getGid() {
        return this.mGid;
    }

    // 获取任务的名称
    public String getName() {
        return this.mName;
    }

    // 获取任务的最后修改时间
    public long getLastModified() {
        return this.mLastModified;
    }

    // 获取删除标记
    public boolean getDeleted() {
        return this.mDeleted;
    }

}
