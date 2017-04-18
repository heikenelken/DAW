import { WallacomicCliPage } from './app.po';

describe('wallacomic-cli App', () => {
  let page: WallacomicCliPage;

  beforeEach(() => {
    page = new WallacomicCliPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
