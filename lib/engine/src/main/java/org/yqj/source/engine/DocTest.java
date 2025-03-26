package org.yqj.source.engine;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/28
 */
public class DocTest {
    public static void main(String[] args) throws Exception{

//        testFirst();

//        testGrammar();

        testMethodReplace();
    }

    public static void testMethodReplace() throws Exception{
        DocTest docTest = new DocTest();

        ExpressRunner runner = new ExpressRunner();
        IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
        runner.addFunctionOfServiceMethod("getTemplate", docTest, "getTemplate", new Class[]{Object[].class}, null);

        //(1)默认的不定参数可以使用数组来代替
        Object r = runner.execute("getTemplate([11,'22', 33L, true])", expressContext, null, false, false);
        System.out.println(r);
        //(2)像java一样,支持函数动态参数调用,需要打开以下全局开关,否则以下调用会失败
        DynamicParamsUtil.supportDynamicParams = true;
        r = runner.execute("getTemplate(11, '22', 33L, true)", expressContext, null, false, false);
        System.out.println(r);
    }

    //等价于getTemplate(Object[] params)
    public Object getTemplate(Object... params) throws Exception{
        String result = "";
        for(Object obj:params){
            result = result + obj + ",";
        }
        return result;
    }

    private static void testGrammar() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String command = "n = 10;\n" +
                "sum = 0;\n" +
                "for(i = 0; i < n; i++) {\n" +
                "   sum = sum + i;\n" +
                "}\n" +
                "return sum;";

//        String command = "a = 1123;\n" +
//                "b = 2;\n" +
//                "maxnum = a > b ? a : b;";

//        String command = "keys = new ArrayList();";
//        String command = "deviceName2Value = new HashMap();";
//        String command = "deviceNames = [\"ng\", \"si\", \"umid\", \"ut\", \"mac\", \"imsi\", \"imei\"];";
//        String command = "keySet = map.keySet();\n" +
//                "objArr = keySet.toArray();\n" +
//                "for (i = 0; i < objArr.length; i++) {\n" +
//                "    key = objArr[i];\n" +
//                "    System.out.println(map.get(key));\n" +
//                "}";

//        String command = "\"hello world\"";

//        String command = "function add(int a, int b){\n" +
//                "    return a + b;\n" +
//                "};\n" +
//                "\n" +
//                "function sub(int a, int b){\n" +
//                "    return a - b;\n" +
//                "};\n" +
//                "\n" +
//                "a = 10;\n" +
//                "return add(a, 4) + sub(a, 9);";

        System.out.println(singleExecuteCommand(context, command));
    }

    public static Object singleExecuteCommand(DefaultContext context, String command) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        return runner.execute(command,  context, null, true, false);
    }

    private static void testFirst() throws Exception{
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }
}
