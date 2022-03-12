package matarillo.example.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout implements AfterNavigationObserver {

    private final H1 pageTitle;
    private final RouterLink home;
    private final RouterLink inMemoryDTO;
    private final RouterLink inMemoryJSON;
    private final RouterLink asyncInMemoryDTO;
    private final RouterLink lazyDTO;

    public MainLayout() {
        // Navigation
        home = new RouterLink("Home", HomeView.class);
        inMemoryDTO = new RouterLink("In-Memory DTO", InMemoryDTOView.class);
        inMemoryJSON = new RouterLink("In-Memory JSON", InMemoryJSONView.class);
        asyncInMemoryDTO = new RouterLink("Asynchronous DTO", AsyncInMemoryDTOView.class);
        lazyDTO = new RouterLink("Lazy DTO", LazyDTOView.class);

        final Tabs tabs = new Tabs(
                new Tab(home),
                new Tab(inMemoryDTO),
                new Tab(inMemoryJSON),
                new Tab(asyncInMemoryDTO),
                new Tab(lazyDTO));

        // Header
        pageTitle = new H1("Home");
        final Header header = new Header(pageTitle);
        header.getStyle().set("min-width", "12rem").set("padding", "1rem");
        addToNavbar(header, tabs);
    }

    private RouterLink[] getRouterLinks() {
        return new RouterLink[] { home, inMemoryDTO, inMemoryJSON, asyncInMemoryDTO, lazyDTO };
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        for (final RouterLink routerLink : getRouterLinks()) {
            if (routerLink.getHighlightCondition().shouldHighlight(routerLink, event)) {
                pageTitle.setText(routerLink.getText());
            }
        }
    }
}
