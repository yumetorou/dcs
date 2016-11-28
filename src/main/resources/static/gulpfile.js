const del = require('del');
const gulp = require('gulp');
const cleanCSS = require('gulp-clean-css');
const colors = require('colors');
const concat = require('gulp-concat');
const plumber = require('gulp-plumber');
const runSequence = require('run-sequence');
const sourcemaps = require('gulp-sourcemaps');
const sysBuilder = require('systemjs-builder');
const tsc = require('gulp-typescript');
const uglify = require('gulp-uglify');
const tsconfig = require('tsconfig-glob');
const browserSync = require('browser-sync').create();
const tscConfig = require('./tsconfig.json');
const typescript = require('gulp-tsc');

gulp.task('clean:dist:js', function () {
  return del('public/dist/js/*');
});

gulp.task('clean:dist:css', function () {
  return del('public/dist/css/*');
});

gulp.task('clean:lib', function () {
  return del('public/lib/**/*');
});

// Compile TypeScript to JS
gulp.task('compile:ts', function () {
  return gulp
    .src(tscConfig.filesGlob)
    .pipe(plumber({
      errorHandler: function (err) {
        console.error('>>> [tsc] Typescript compilation failed'.bold.green);
        this.emit('end');
      }
    }))
    .pipe(sourcemaps.init())
    .pipe(tsc(tscConfig.compilerOptions))
    .pipe(sourcemaps.write('.'))
    .pipe(gulp.dest('public/dist/js'));
});

// Generate systemjs-based builds
gulp.task('bundle:js', function () {
  var builder = new sysBuilder('public', './system.config.js');
  return builder.buildStatic('app', 'public/dist/js/app.min.js')
    .then(function () {
      return del(['public/dist/js/**/*', '!public/dist/js/app.min.js']);
    })
    .catch(function (err) {
      console.error('>>> [systemjs-builder] Bundling failed'.bold.green, err);
    });
});

// Minify JS bundle
gulp.task('minify:js', function () {
  return gulp
    .src('public/dist/js/app.min.js')
    .pipe(uglify())
    .pipe(gulp.dest('public/dist/js'))
    .pipe(browserSync.stream());
  browserSync.reload();

});

// Concat and minify CSS
gulp.task('minify:css', function () {
  // concat and minify global css files
  gulp
    .src('styles/css/*.css')
    .pipe(concat('app.min.css'))
    .pipe(cleanCSS())
    .pipe(gulp.dest('public/dist/css'))
    .pipe(browserSync.stream());
  browserSync.reload();

});

// Copy dependencies
gulp.task('copy:libs', function () {
  gulp.src(['node_modules/rxjs/**/*'])
    .pipe(gulp.dest('public/lib/js/rxjs'));

  // concatenate non-angular2 libs, shims & systemjs-config
  gulp.src([
      'node_modules/jquery/dist/jquery.min.js',
      'node_modules/bootstrap/dist/js/bootstrap.min.js',
      'node_modules/es6-shim/es6-shim.min.js',
      'node_modules/es6-promise/dist/es6-promise.min.js',
      'node_modules/zone.js/dist/zone.js',
      'node_modules/reflect-metadata/Reflect.js',
      'node_modules/systemjs/dist/system.src.js',
      'system.config.js'
    ])
    .pipe(concat('vendors.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('public/lib/js'));

  // copy source maps
  gulp.src([
    'node_modules/es6-shim/es6-shim.map',
    'node_modules/reflect-metadata/Reflect.js.map',
    'node_modules/systemjs/dist/system-polyfills.js.map'
  ]).pipe(gulp.dest('public/lib/js'));

  gulp.src([
    'node_modules/bootstrap/dist/css/bootstrap.*'
  ]).pipe(gulp.dest('public/lib/css'));

  gulp.src([
    'styles/fonts/*'
  ]).pipe(gulp.dest('public/dist/fonts'));

  gulp.src(['node_modules/angular2-datatable/**/*'
  ]).pipe(gulp.dest('public/lib/js/angular2-datatable'));

  gulp.src(['node_modules/angular2-data-table/**/*'
  ]).pipe(gulp.dest('public/lib/js/angular2-data-table'));

  gulp.src(['node_modules/lodash/**/*'
  ]).pipe(gulp.dest('public/lib/js/lodash'));

  gulp.src(['node_modules/@ng-bootstrap/ng-bootstrap/**/*'
  ]).pipe(gulp.dest('public/lib/js/@ng-bootstrap/ng-bootstrap'));

  return gulp.src(['node_modules/@angular/**/*'])
    .pipe(gulp.dest('public/lib/js/@angular'));
});

// Update the tsconfig files based on the glob pattern
gulp.task('tsconfig-glob', function () {
  return tsconfig({
    configPath: '.',
    indent: 2
  });
});

gulp.task('serve', ['scripts', 'styles'], function () {
  // Serve files from the root of this project
  browserSync.init({
    server: false,
    proxy: 'localhost:8080',
    port: 3000,
    ui: {
      port: 3001,
      weinre: {
        port: 8080
      }
    },
    logLevel: 'info',
    open: false,
    browser: "default",
    watchOptions: {ignored: 'node_modules'}


  });

  gulp.watch(['app/**/*.ts', 'app/*.ts'], ['scripts']);
  gulp.watch('styles/**/*.css', ['styles']);
  gulp.watch(['app/**/*.html', 'app/*.html']).on('change', browserSync.reload);

});

gulp.task('clean', ['clean:dist:js', 'clean:dist:css', 'clean:lib']);

gulp.task('copy', function (callback) {
  runSequence('clean:lib', 'copy:libs', callback);
});
gulp.task('scripts', function (callback) {
  runSequence('clean:dist:js', 'compile:ts', 'bundle:js', 'minify:js', callback);
});
gulp.task('styles', function (callback) {
  runSequence('clean:dist:css', 'minify:css', callback);
});
gulp.task('build', function (callback) {
  runSequence('copy', 'scripts', 'styles', callback);
});

gulp.task('default', function (callback) {
  runSequence('build', callback);
});
