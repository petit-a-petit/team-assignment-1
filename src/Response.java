import java.util.List;

public class Response {

    private int status;
    private Page pageInfo;
    private List<Integer> subPages;
    private List<Integer> breadCrumbs;

    public Response(int status){
        this.status = status;
    }

    public Response(int status, Page page, List<Integer> subPages, List<Integer> breadCrumbs) {
        this.status = status;
        this.pageInfo  = page;
        this.subPages = subPages;
        this.breadCrumbs = breadCrumbs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Page getPage() {
        return pageInfo;
    }

    public void setPage(Page page) {
        this.pageInfo = page;
    }

    public List<Integer> getSubPages() {
        return subPages;
    }

    public void setSubPages(List<Integer> subPages) {
        this.subPages = subPages;
    }

    public List<Integer> getBreadCrumbs() {
        return breadCrumbs;
    }

    public void setBreadCrumbs(List<Integer> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }
}
