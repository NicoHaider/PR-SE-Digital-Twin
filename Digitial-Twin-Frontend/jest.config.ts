import type { Config } from 'jest';

const config: Config = {
  preset: 'jest-preset-angular',
  setupFilesAfterEnv: ['<rootDir>/src/setup-jest.ts'],
  moduleNameMapper: {
    '@app/(.*)': '<rootDir>/src/app/$1',
    '@assets/(.*)': '<rootDir>/src/assets/$1',
    '@env': '<rootDir>/src/environments/environment'
  },
  transform: {
    '^.+\\.(ts|html)$': ['jest-preset-angular', {
      tsconfig: '<rootDir>/tsconfig.spec.json',
      stringifyContentPathRegex: '\\.html$',
      diagnostics: false
    }]
  },
  testPathIgnorePatterns: ['<rootDir>/node_modules/', '<rootDir>/dist/']
};

export default config;
