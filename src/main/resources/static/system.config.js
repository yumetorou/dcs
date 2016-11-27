// map tells the System loader where to look for things
var map = {
  'app': 'dist/js',
  'rxjs': 'lib/js/rxjs',
  '@angular': 'lib/js/@angular',
  'zone.js': 'lib/js/zone.js/dist',
  'angular2-datatable': 'lib/js/angular2-datatable',
  'lodash': 'lib/js/lodash/lodash.js'
};

// packages tells the System loader how to load when no filename and/or no extension
var packages = {
  'app': {main: 'main', defaultExtension: 'js'},
  'rxjs': {defaultExtension: 'js'},
  'zone.js': {main: 'zone', defaultExtension: 'js'},
  'angular2-datatable': {main: 'index.js', defaultExtension: 'js'}
};

var packageNames = [
  '@angular/common',
  '@angular/compiler',
  '@angular/core',
  '@angular/forms',
  '@angular/http',
  '@angular/platform-browser',
  '@angular/platform-browser-dynamic',
  '@angular/router',
  '@angular/testing',
  '@angular/upgrade'
];

// add package entries for angular packages in the form '@angular/common': { main: 'index.js', defaultExtension: 'js' }
packageNames.forEach(function (pkgName) {
  packages[pkgName] = {main: 'index.js', defaultExtension: 'js'};
});

System.config({
  map: map,
  packages: packages
});
