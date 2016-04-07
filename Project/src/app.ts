import {bootstrap} from 'angular2/platform/browser';
import {ProjectApp} from './app/project';
import {ROUTER_PROVIDERS} from 'angular2/router';

bootstrap(ProjectApp, [
  ROUTER_PROVIDERS
]);
