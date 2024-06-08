const User = require("./../../models/userModel");
const UsersImages = require("./../../models/userModelIamges");
const path = require("path");

exports.createUser = async (req, res) => {
  try {
    const hashedPassword = await bcrypt.hash(req.body.password, 10);
    const user = await User.create({ ...req.body, password: hashedPassword });
    res.status(200).json({isSuccess: true, user: user});
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.createUserWithImage = async (req, res) => {
  try {
    const newUser = {
      userName: req.body.userName,
      email: req.body.email,
      password: req.body.password,
      avatar: `${req.protocol}://${req.get("host")}/api/images/${
        req.file.filename
      }`, // Tên file ảnh sẽ được lưu trong cơ sở dữ liệu
    };

    const user = await User.create(newUser);
    res.status(201).json(user);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.createUserWithImages = async (req, res) => {
  try {
    const { files } = req;
    const urlImages = files.map(
      (file) => `${req.protocol}://${req.get("host")}/api/images`
    );
    const newUser = {
      userName: req.body.userName,
      email: req.body.email,
      password: req.body.password,
      avatar: urlImages,
    };

    const user = await UsersImages.create(newUser);
    res.status(201).json(user);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getAllUsers = async (req, res) => {
  try {
    const users = await User.find({});
    res.status(200).json(users);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getUserById = async (req, res) => {
  try {
    const { id } = req.params;
    const user = await User.findById(id);
    res.status(200).json(user);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
exports.changePassword = async (req, res) => {
  try {
      const { id } = req.params;
      const { password, ...userData } = req.body;
      if (password) {
          const hashedPassword = await bcrypt.hash(password, 10);
          userData.password = hashedPassword;
      }
      const updatedUser = await User.findByIdAndUpdate(id, userData, { new: true });
      if (!updatedUser) {
          return res.status(404).json({ message: `User not found with ID: ${id}` });
      }

      res.status(200).json(updatedUser);
  } catch (error) {
      res.status(500).json({ message: error.message });
  }
};

exports.updateFavorite = async (req, res) => {
  try {
    const { id } = req.params;
    const { favorites, ...userData } = req.body;

    if (favorites) {
      const updatedUser = await User.findByIdAndUpdate(id, { ...userData, favorites: favorites }, { new: true });
      if (!updatedUser) {
        return res.status(404).json({ message: `User not found with ID: ${id}` });
      }
      res.status(200).json({isSuccess: true, user: updatedUser});
    } 
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.updatePaymentMethod = async (req, res) => {
  try {
    const { id } = req.params;
    const { paymentMethod, ...userData } = req.body;
    if (paymentMethod) {
      const updatedUser = await User.findByIdAndUpdate(id, { ...userData, paymentMethod: paymentMethod }, { new: true });
      if (!updatedUser) {
        return res.status(404).json({ message: `User not found with ID: ${id}` });
      }
      res.status(200).json({isSuccess: true, user: updatedUser});
    } 
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
exports.updateInfo = async (req, res) => {
  try {
    const { id } = req.params;
    const newUser = req.body;
    
    if (newUser) {
      const updatedUser = await User.findByIdAndUpdate(id, newUser, { new: true, runValidators: true });
      
      if (!updatedUser) {
        return res.status(404).json({ message: `User not found with ID: ${id}` });
      }
      
      return res.status(200).json({ isSuccess: true, user: updatedUser });
    } else {
      return res.status(400).json({ message: 'No user data provided' });
    }
  } catch (error) {
    return res.status(500).json({ message: error.message });
  }
};



exports.deleteUser = async (req, res) => {
  try {
    const { id } = req.params;
    const user = await User.findByIdAndDelete(id);
    if (!user) {
      return res.status(404).json({ message: `User not found with ID: ${id}` });
    }
    res.status(200).json(user);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const bcrypt = require('bcrypt');

exports.login = async (req, res) => {
  try {
    const { email, password } = req.body;

    // Kiểm tra xem email và password có được cung cấp hay không
    if (!email || !password) {
      return res.status(400).json({ message: "Email and password are required" });
    }

    // Tìm người dùng trong cơ sở dữ liệu bằng email
    const user = await User.findOne({ email });

    // Kiểm tra xem người dùng có tồn tại không
    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }

    // Kiểm tra mật khẩu có đúng không
    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(401).json({ message: "Incorrect password" });
    }

    res.status(200).json({isSuccess: true, user: user});
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
