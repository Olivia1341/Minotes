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
import android.util.Log;

import net.micode.notes.tool.GTaskStringUtils;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * MetaData类，用于处理与Google Tasks相关的元数据信息。
 * 继承自Task类，扩展了任务的基本功能，专注于元数据的存储和管理。
*/
public class MetaData extends Task {
    // 日志标签，用于在日志输出时标识信息来源。
    private final static String TAG = MetaData.class.getSimpleName();

    // 相关任务的全局唯一标识符，用于关联元数据与具体任务。
    private String mRelatedGid = null;

    // 设置元数据信息，将给定的任务ID和元数据信息进行封装存储。
    public void setMeta(String gid, JSONObject metaInfo) {
        try {
            // 将任务ID添加到元数据JSON对象中
            metaInfo.put(GTaskStringUtils.META_HEAD_GTASK_ID, gid);
        } catch (JSONException e) {
            // 记录设置任务ID失败的错误日志
            Log.e(TAG, "failed to put related gid");
        }
        // 将元数据信息转换为字符串并存储
        setNotes(metaInfo.toString());
        // 设置元数据的名称
        setName(GTaskStringUtils.META_NOTE_NAME);
    }

    /*
     * 获取与元数据关联的任务全局唯一标识符。
     * 返回任务的全局唯一标识符
     */
    public String getRelatedGid() {
        return mRelatedGid;
    }

    // 判断元数据是否值得保存，即是否有有效的笔记内容。
    @Override
    public boolean isWorthSaving() {
        return getNotes() != null;
    }

    // 通过远程JSON对象设置元数据内容，解析并提取任务ID。
    @Override
    public void setContentByRemoteJSON(JSONObject js) {
        // 调用父类方法（super）设置基本内容
        super.setContentByRemoteJSON(js);
        if (getNotes() != null) {
            try {
                JSONObject metaInfo = new JSONObject(getNotes().trim());
                mRelatedGid = metaInfo.getString(GTaskStringUtils.META_HEAD_GTASK_ID);
            } catch (JSONException e) {
                Log.w(TAG, "failed to get related gid");
                mRelatedGid = null;
            }
        }
    }

    // js是包含元数据信息的本地JSON对象
    @Override
    public void setContentByLocalJSON(JSONObject js) {
        // this function should not be called
        throw new IllegalAccessError("MetaData:setContentByLocalJSON should not be called");
    }

    // 从元数据内容获取本地JSON对象，此处不应被调用。
    @Override
    public JSONObject getLocalJSONFromContent() {
        throw new IllegalAccessError("MetaData:getLocalJSONFromContent should not be called");
    }

    // 获取同步操作的类型，此处不应被调用。
    @Override
    public int getSyncAction(Cursor c) {
        throw new IllegalAccessError("MetaData:getSyncAction should not be called");
    }

}
