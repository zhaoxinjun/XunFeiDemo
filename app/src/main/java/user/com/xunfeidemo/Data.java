package user.com.xunfeidemo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class Data {

    /**
     * sn : 1
     * ls : false
     * bg : 0
     * ed : 0
     * ws : [{"bg":0,"cw":[{"sc":0,"w":"上网"}]},{"bg":0,"cw":[{"sc":0,"w":"你"}]},{"bg":0,"cw":[{"sc":0,"w":"又"}]},{"bg":0,"cw":[{"sc":0,"w":"不"}]},{"bg":0,"cw":[{"sc":0,"w":"听话"}]},{"bg":0,"cw":[{"sc":0,"w":"，"}]},{"bg":0,"cw":[{"sc":0,"w":"不"}]},{"bg":0,"cw":[{"sc":0,"w":"好好"}]},{"bg":0,"cw":[{"sc":0,"w":"学习"}]}]
     */

    public int sn;
    public boolean ls;
    public int bg;
    public int ed;
    public List<WsBean> ws;

    public static class WsBean {
        /**
         * bg : 0
         * cw : [{"sc":0,"w":"上网"}]
         */

        public int bg;
        public List<CwBean> cw;

        public static class CwBean {
            /**
             * sc : 0
             * w : 上网
             */

            public int sc;
            public String w;
        }
    }
}
