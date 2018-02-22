/*author wangjf 02/10/2018 */

package com.qiyi.hwmessenger;

interface IHWControllerClient
{
    String readSysfs(String path);
    String writeSysfs(String path, String setValue);
}