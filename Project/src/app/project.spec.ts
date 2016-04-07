import {describe, it, expect, beforeEachProviders, inject} from 'angular2/testing';
import {ProjectApp} from '../app/project';

beforeEachProviders(() => [ProjectApp]);

describe('App: Project', () => {
  it('should have the `defaultMeaning` as 42', inject([ProjectApp], (app: ProjectApp) => {
    expect(app.defaultMeaning).toBe(42);
  }));

  describe('#meaningOfLife', () => {
    it('should get the meaning of life', inject([ProjectApp], (app: ProjectApp) => {
      expect(app.meaningOfLife()).toBe('The meaning of life is 42');
      expect(app.meaningOfLife(22)).toBe('The meaning of life is 22');
    }));
  });
});

