package reflectapitest;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class retest1
{
  /*
  * Instantiate EcoreFactory
  */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void main(String[] args){
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EClass purchaseOrderClass = ecoreFactory.createEClass();
    purchaseOrderClass.setName("PurchaseOrder");
    
    EAttribute shipTo = ecoreFactory.createEAttribute();
    shipTo.setName("shipTo");
    shipTo.setEType(EcorePackage.Literals.ESTRING);
    purchaseOrderClass.getEStructuralFeatures().add(shipTo);
    EAttribute billTo = ecoreFactory.createEAttribute();
    billTo.setName("billTo");
    billTo.setEType(EcorePackage.Literals.ESTRING);
    purchaseOrderClass.getEStructuralFeatures().add(billTo);
    
    EClass itemClass = ecoreFactory.createEClass();
    itemClass.setName("Item");
    
    EAttribute productName = ecoreFactory.createEAttribute();
    productName.setName("productName");
    productName.setEType(EcorePackage.Literals.ESTRING);
    itemClass.getEStructuralFeatures().add(productName);
    EAttribute quantity = ecoreFactory.createEAttribute();
    quantity.setName("quantity");
    quantity.setEType(EcorePackage.Literals.EINT);
    itemClass.getEStructuralFeatures().add(quantity);
    EAttribute price = ecoreFactory.createEAttribute();
    price.setName("price");
    price.setEType(EcorePackage.Literals.EFLOAT);
    itemClass.getEStructuralFeatures().add(price);
    
    EReference items = ecoreFactory.createEReference();
    items.setName("items");
    items.setEType(itemClass);
    items.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);//无界多重性的包容引用
    items.setContainment(true);
    purchaseOrderClass.getEStructuralFeatures().add(items);
    
    EPackage poPackage = ecoreFactory.createEPackage();
    poPackage.setName("po");
    poPackage.setNsPrefix("po");
    poPackage.setNsURI("");
    poPackage.getEClassifiers().add(purchaseOrderClass);
    poPackage.getEClassifiers().add(itemClass);

    EFactory poFactory = poPackage.getEFactoryInstance();
    EObject purchaseOrder = poFactory.create(purchaseOrderClass);
    purchaseOrder.eSet(shipTo, "beijing");
    
    EObject item = poFactory.create(itemClass);
    item.eSet(productName, "apple");
    item.eSet(quantity, new Integer(12));
    item.eSet(price, new Float(1.2));
    ((EList)purchaseOrder.eGet(items)).add(item);
    
  }
  

}
