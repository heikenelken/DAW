import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';

@Component({
  selector: 'footer',
  templateUrl: 'app/footer.component.html',
//providers:  [BookService],
  directives: [ROUTER_DIRECTIVES]
})

export class FooterComponent { }
