/// <reference path="../typings/main.d.ts" />

import { ProjectPage } from './app.po';

describe('project App', function() {
  let page: ProjectPage;

  beforeEach(() => {
    page = new ProjectPage();
  })

  it('should display message saying app works', () => {
    page.navigateTo()
    expect(page.getParagraphText()).toEqual('project Works!');
  });
});
