package site.hnfy258.RPCVer2.server;

import site.hnfy258.RPCVer2.common.Blog;
import site.hnfy258.RPCVer2.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).useId(1).title("我的博客").build();
        System.out.println("查询到博客信息："+blog);
        return blog;
    }
}
