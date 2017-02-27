[![](https://travis-ci.org/imagej/imagej-deprecated.svg?branch=master)](https://travis-ci.org/imagej/imagej-deprecated)

ImageJ Deprecated Classes
=========================

This project contains ImageJ subprojects which have been deprecated in favor of
newer code. The following packages exist:

* __`net.imagej.measure`__ - The old ImageJ2 measurement and statistics plugins,
  which are being rewritten as ImageJ Ops plugins.
* __`net.imagej.operator`__ - The old ImageJ2 image calculator plugins,
  which have migrated into ImageJ Ops.
* __`net.imagej.threshold`__ - The old ImageJ2 thresholding plugins,
  which have migrated into ImageJ Ops.
* __`net.imglib2.meta`__ - The old ImgLib2 Meta project,
  which has migrated into ImageJ Common.
* __`net.imglib2.ops`__ - The old ImgLib2 OPS project,
  which is migrating to ImageJ Ops in stages.

There are a few other "one-off" classes here as well:

* __`net.imagej.overlay.ThresholdOverlay`__ -
  ImageJ2 Overlay implementation which depends
  on the old `net.imagej.threshold` package.
* __`net.imglib2.AbstractAnnotatedSpace`__ -
  Moved to `net.imagej.space.AbstractAnnotatedSpace` in ImageJ Common.
* __`net.imglib2.AnnotatedSpace`__ -
  Moved to `net.imagej.space.AnnotatedSpace` in ImageJ Common.
* __`net.imglib2.Axis`__ -
  Moved to `net.imagej.axis.Axis` in ImageJ Common.
