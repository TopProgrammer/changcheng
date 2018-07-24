new Vue({
    el: '.page-sidebar',
    data:{
        pageSidebar: '<a class="logo-box" href="index.html">\n' +
            '            <!--<img src="app/images/logo3.png" width="80">-->\n' +
        '            <span id="appName">{{appName}} </span>\n' +
        '            <i class="icon-radio_button_unchecked" id="fixed-sidebar-toggle-button"></i>\n' +
        '            <i class="icon-close" id="sidebar-toggle-button-close"></i>\n' +
        '        </a>\n' +
        '\n' +
        '        <div class="page-sidebar-inner">\n' +
        '            <div class="page-sidebar-menu">\n' +
        '                <ul class="accordion-menu">\n' +
        '                    <li class="active-page">\n' +
        '                        <a href="index.html">\n' +
        '                            <i class="menu-icon icon-home4"></i><span>Dashboard</span>\n' +
        '                        </a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="email.html">\n' +
        '                            <i class="menu-icon icon-inbox"></i><span>Email</span>\n' +
        '                        </a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-flash_on"></i><span>UI Kits</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="ui-alerts.html">Alerts</a></li>\n' +
        '                            <li><a href="ui-buttons.html">Buttons</a></li>\n' +
        '                            <li><a href="ui-icons.html">Icons</a></li>\n' +
        '                            <li><a href="ui-typography.html">Typography</a></li>\n' +
        '                            <li><a href="ui-notifications.html">Notifications</a></li>\n' +
        '                            <li><a href="ui-modals.html">Modals</a></li>\n' +
        '                            <li><a href="ui-progress.html">Progress Bars</a></li>\n' +
        '                            <li><a href="ui-tabs-accordions.html">Tabs &amp; Accordions</a></li>\n' +
        '                            <li><a href="ui-tree-view.html">Tree View</a></li>\n' +
        '                            <li><a href="ui-nestable.html">Nestable</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-layers"></i><span>Layouts</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="layout-blank.html">Blank Page</a></li>\n' +
        '                            <li><a href="layout-boxed.html">Boxed Layout</a></li>\n' +
        '                            <li><a href="layout-collapsed-sidebar.html">Collapsed Sidebar</a></li>\n' +
        '                            <li><a href="layout-fixed-header.html">Fixed Header</a></li>\n' +
        '                            <li><a href="layout-fixed-sidebar.html">Fixed Sidebar</a></li>\n' +
        '                            <li><a href="layout-fixed-sidebar-header.html">Fixed Sidebar &amp; Header</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-code"></i><span>Forms</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="form-elements.html">Elements</a></li>\n' +
        '                            <li><a href="form-wizard.html">Form Wizard</a></li>\n' +
        '                            <li><a href="form-file-upload.html">File Upload</a></li>\n' +
        '                            <li><a href="form-image-crop.html">Image Crop</a></li>\n' +
        '                            <li><a href="form-image-zoom.html">Image Zoom</a></li>\n' +
        '                            <li><a href="form-x-editable.html">X-editable</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-format_list_bulleted"></i><span>Tables</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="table-static.html">Static</a></li>\n' +
        '                            <li><a href="table-responsive.html">Responsive</a></li>\n' +
        '                            <li><a href="table-data.html">Data Tables</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="charts.html">\n' +
        '                            <i class="menu-icon icon-show_chart"></i><span>Charts</span>\n' +
        '                        </a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-my_location"></i><span>Maps</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="maps-google.html">Google</a></li>\n' +
        '                            <li><a href="maps-vector.html">Vector</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="javascript:void(0)">\n' +
        '                            <i class="menu-icon icon-star"></i><span>Extra</span><i class="accordion-icon fa fa-angle-left"></i>\n' +
        '                        </a>\n' +
        '                        <ul class="sub-menu">\n' +
        '                            <li><a href="invoice.html">Invoice</a></li>\n' +
        '                            <li><a href="404.html">404 Page</a></li>\n' +
        '                            <li><a href="500.html">500 Page</a></li>\n' +
        '                            <li><a href="profile.html">Profile</a></li>\n' +
        '                            <li><a href="login.html">Login</a></li>\n' +
        '                            <li><a href="register.html">Register</a></li>\n' +
        '                            <li><a href="lockscreen.html">Lockscreen</a></li>\n' +
        '                            <li><a href="todo.html">Todo</a></li>\n' +
        '                            <li><a href="gallery.html">Gallery</a></li>\n' +
        '                            <li><a href="pricing-tables.html">Pricing Tables</a></li>\n' +
        '                            <li><a href="timeline.html">Timeline</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li class="menu-divider"></li>\n' +
        '                    <li>\n' +
        '                        <a href="index.html">\n' +
        '                            <i class="menu-icon icon-help_outline"></i><span>Documentation</span>\n' +
        '                        </a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="index.html">\n' +
        '                            <i class="menu-icon icon-public"></i><span>Changelog</span><span class="label label-danger">1.0</span>\n' +
        '                        </a>\n' +
        '                    </li>\n' +
        '                </ul>\n' +
        '            </div>\n' +
        '        </div>'
    }
});


new Vue({
    el: '#appName',// element 指Vue实例挂载的元素节点值可以是 CSS 选择符，或实际 HTML 元素，或返回 HTML 元素的函数。官方文档解释还是挺好的
    data: {
        appName: '长城试剑'
    }
});

