import { HackatomUiPage } from './app.po';

describe('hackatom-ui App', function() {
  let page: HackatomUiPage;

  beforeEach(() => {
    page = new HackatomUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
