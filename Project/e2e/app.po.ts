export class ProjectPage {
  navigateTo() { return browser.get('/'); }
  getParagraphText() { return element(by.css('Project-app p')).getText(); }
}
