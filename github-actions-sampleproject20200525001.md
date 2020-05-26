Notes from exploring/experimenting with GitHub Actions and python sample project

Working with `/Users/rpowell/dev/learning-python-packaging/packaging_tutorial_20200525001/sampleproject20200525001`

Using `venv`:

    python -m venv venv_20200525001
    source venv_20200525001/bin/activate

Notes:

    .gitignore
    venv/

--------------------------------------------------------------------------------

Reference:
* https://packaging.python.org/tutorials/packaging-projects/

Source code
* https://github.com/pypa/sampleproject

Clone

    git clone https://github.com/pypa/sampleproject sampleproject20200525001


# Build and Test

    python3 setup.py sdist bdist_wheel
    pip install dist/sampleproject-1.3.1-py3-none-any.whl 

## Testing

    pip install pytest
    pytest tests/test_simple.py

    pip install flake8 pytest


# Github repository

    sampleproject20200525001
    
    git init
    git add .
    git commit -m
    
    git remote add origin https://github.com/rppowellemail/sampleproject20200525001.git
    git config user.name "Rob Powell"
    git config user.email "rppowellemail@gmail.com"
    git config --local credential.helper ""
    git push -u origin master


# Actions

Setting up action - using python-package.yaml

`.github/workflows/python-package.yml`
