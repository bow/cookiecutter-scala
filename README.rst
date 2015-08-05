cookiecutter-scala
==================

Opinionated `cookiecutter <https://github.com/audreyr/cookiecutter>`_ template for a Scala project.

* Scala 2.11.6 targeting Java 1.8

* SBT-based build management (via ``build.scala``), with the following plugins:

  - `sbt-header <https://github.com/sbt/sbt-header>`_ for source file headers
  - `sbt-scalariform <https://github.com/sbt/sbt-scalariform>`_ for code style
  - `sbt-scapegoat <https://github.com/sksamuel/sbt-scapegoat>`_ for static analysis
  - `sbt-scoverage <https://github.com/scoverage/sbt-scoverage>`_ for coverage analysis
  - `sbt-site <https://github.com/sbt/sbt-site>`_ for project site generation

* Testing with `specs2 <https://etorreborre.github.io/specs2/>`_

* Travis-CI: Ready for Travis Continuous Integration testing

* Free software: Apache 2.0 license

Usage
-----

Generate a Scala project::

    cookiecutter https://github.com/bow/cookiecutter-scala.git

Then:

* Create a repo and put it there.
* Add the repo to your Travis CI account.
